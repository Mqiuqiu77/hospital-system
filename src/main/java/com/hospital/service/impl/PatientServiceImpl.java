package com.hospital.service.impl;

import com.hospital.dto.PatientUpdateDTO;
import com.hospital.entity.Patient;
import com.hospital.exception.BusinessException;
import com.hospital.mapper.PatientMapper;
import com.hospital.service.PatientService;
import com.hospital.vo.PatientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tools.jackson.databind.ObjectMapper;
import java.util.UUID;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PatientServiceImpl implements PatientService {
    private static final String PATIENT_DETAIL_CACHE_PREFIX = "patient:detail:";
    private static final String NULL_CACHE_VALUE = "__NULL__";
    private static final String PATIENT_DETAIL_LOCK_PREFIX = "lock:patient:detail:";

    private final PatientMapper patientMapper;
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    private boolean tryLock(String lockKey, String lockValue){
        Boolean success = stringRedisTemplate.opsForValue()
                .setIfAbsent(lockKey, lockValue, Duration.ofSeconds(10));
        return Boolean.TRUE.equals(success);
    }

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT =
            new DefaultRedisScript<>(
                    "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                            "return redis.call('del', KEYS[1]) " +
                            "else return 0 end",
                    Long.class
            );

    private void unlock(String lockKey, String lockValue) {
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                List.of(lockKey),
                lockValue
        );
    }

    public PatientServiceImpl(PatientMapper patientMapper,
                              ObjectMapper objectMapper,
                              StringRedisTemplate stringRedisTemplate){
        this.patientMapper = patientMapper;
        this.objectMapper = objectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private Duration randomPatientCacheTtl(){
        long randomMinutes = ThreadLocalRandom.current().nextLong(0, 6);
        return Duration.ofMinutes(10+randomMinutes);
    }

    //    负责读取缓存
    private PatientVO getPatientFromCache(String cacheKey){
        String cacheJson = stringRedisTemplate.opsForValue().get(cacheKey);
        if(NULL_CACHE_VALUE.equals(cacheJson)){
            throw new BusinessException("患者不存在");
        }
        if(StringUtils.hasText(cacheJson)){
            return objectMapper.readValue(cacheJson,PatientVO.class);
        }
        return null;
    }

//    负责查询数据库并回填
    private PatientVO loadPatientAndCache(Long id, String cacheKey){


        Patient patient = patientMapper.selectById(id);

        if(patient==null){
            stringRedisTemplate.opsForValue().set(cacheKey,NULL_CACHE_VALUE,Duration.ofMinutes(2));

            throw new BusinessException("患者不存在");
        }

        PatientVO patientVO = new PatientVO();
        BeanUtils.copyProperties(patient,patientVO);

        String patientJson = objectMapper.writeValueAsString(patientVO);

        stringRedisTemplate.opsForValue().set(cacheKey,patientJson,
                randomPatientCacheTtl());

        return patientVO;
    }

//    负责编排流程。
    @Override
    public PatientVO getPatientById(Long id) {
        String cacheKey = PATIENT_DETAIL_CACHE_PREFIX + id;
        String lockKey = PATIENT_DETAIL_LOCK_PREFIX + id;
        String lockValue = UUID.randomUUID().toString();


        for (int attempt = 0; attempt < 20; attempt++) {
            // 每轮首先查询缓存
            PatientVO cachedPatient = getPatientFromCache(cacheKey);

            if (cachedPatient != null) {
                return cachedPatient;
            }

            // 缓存未命中则尝试获得锁
            if (tryLock(lockKey, lockValue)) {
                try {
                    // 二次检查缓存
                    PatientVO cachedPatient2 = getPatientFromCache(cacheKey);
                    if (cachedPatient2 != null) {
                        return cachedPatient2;
                    }
                    // 二次检查仍未命中：
                    return loadPatientAndCache(id, cacheKey);
                } finally {
                    unlock(lockKey, lockValue);
                }
            }

            // 没获得锁则等待50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new BusinessException("查询患者信息被中断");
            }


        }

        throw new BusinessException("系统繁忙，请稍后重试");

    }

    @Override
    public void updatePatient(Long id, PatientUpdateDTO dto) {
        Patient patient = new Patient();
        patient.setId(id);

        BeanUtils.copyProperties(dto,patient);

        int affectedRows = patientMapper.updateById(patient);

        if (affectedRows == 0){
            throw new BusinessException("患者不存在");
        }

        stringRedisTemplate.delete(PATIENT_DETAIL_CACHE_PREFIX + id);
    }
}
