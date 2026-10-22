package com.hospital.service.impl;

import com.hospital.dto.HospitalUpdateDTO;
import com.hospital.entity.Hospital;
import com.hospital.exception.BusinessException;
import com.hospital.mapper.HospitalMapper;
import com.hospital.service.HospitalService;
import com.hospital.vo.HospitalVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
   private final HospitalMapper hospitalMapper;
   private final StringRedisTemplate stringRedisTemplate;
   private final ObjectMapper objectMapper;

   private static final String HOSPITAL_LIST_CACHE_KEY = "hospital:list";

    public HospitalServiceImpl(HospitalMapper hospitalMapper,
                               StringRedisTemplate stringRedisTemplate,
                               ObjectMapper objectMapper) {
        this.hospitalMapper = hospitalMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }



    private HospitalVO toVo(Hospital hospital){
        HospitalVO hospitalVO = new HospitalVO();

        BeanUtils.copyProperties(hospital,hospitalVO);
        return hospitalVO;
    }


    @Override
    public List<HospitalVO> listHospitals() {
        String cacheJson =
                stringRedisTemplate.opsForValue().get(HOSPITAL_LIST_CACHE_KEY);

        if(StringUtils.hasText(cacheJson)){
            return objectMapper.readValue(cacheJson, new TypeReference<List<HospitalVO>>() {
            });
        }

        List<Hospital> hospitals = hospitalMapper.selectList(null);

        List<HospitalVO> hospitalVOList = hospitals.stream().map(this::toVo).toList();

        String hospitalListJson = objectMapper.writeValueAsString(hospitalVOList);

        stringRedisTemplate.opsForValue().set(HOSPITAL_LIST_CACHE_KEY,hospitalListJson,
                Duration.ofMinutes(30));


        return hospitalVOList;
    }

    @Override
    public void updateHospital(Long id, HospitalUpdateDTO dto) {
        Hospital hospital = new Hospital();
        hospital.setId(id);
        BeanUtils.copyProperties(dto,hospital);

        int affectedRows = hospitalMapper.updateById(hospital);

        if(affectedRows == 0){
            throw new BusinessException("医院不存在");
        }

        stringRedisTemplate.delete(HOSPITAL_LIST_CACHE_KEY);
    }
}
