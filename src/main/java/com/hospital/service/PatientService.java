package com.hospital.service;

import com.hospital.dto.PatientUpdateDTO;
import com.hospital.vo.PatientVO;

public interface PatientService {
    PatientVO getPatientById(Long id);

    void updatePatient(Long id, PatientUpdateDTO dto);
}
