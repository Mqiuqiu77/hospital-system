package com.hospital;


import com.hospital.vo.HospitalVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HospitalVOJsonTests {
    private final ObjectMapper objectMapper;

    @Autowired
    public HospitalVOJsonTests(ObjectMapper  objectMapper){
        this.objectMapper = objectMapper;
    }

    @Test
    public void test(){
        HospitalVO hospitalVO = new HospitalVO();
        hospitalVO.setId(666L);
        hospitalVO.setName("bit");
        hospitalVO.setAddress("重庆");
        hospitalVO.setPhone("150");
        hospitalVO.setHospitalLevel("三级");

        List<HospitalVO> hospitalList = new ArrayList<>();
        hospitalList.add(hospitalVO);

        String json = objectMapper.writeValueAsString(hospitalList);

        List<HospitalVO> result = objectMapper.readValue(json, new TypeReference<List<HospitalVO>>() {});

        assertEquals(1,result.size());
        assertEquals("bit",result.get(0).getName());


    }


}

