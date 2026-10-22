package com.hospital.service;

import com.hospital.dto.HospitalUpdateDTO;
import com.hospital.vo.HospitalVO;

import java.util.List;

public interface HospitalService {
    List<HospitalVO> listHospitals();

    void updateHospital(Long id, HospitalUpdateDTO dto);
}
