package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.dto.PatientUpdateDTO;
import com.hospital.service.PatientService;
import com.hospital.vo.PatientVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;


    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public Result<PatientVO> patientInfo(@PathVariable Long id){
        PatientVO patientVO = patientService.getPatientById(id);
        return Result.success(patientVO);
    }

    @PutMapping("/{id}")
    public Result<Void> updatePatient(@PathVariable Long id,
                                      @Valid @RequestBody PatientUpdateDTO patientUpdateDTO){
        patientService.updatePatient(id,patientUpdateDTO);
        return Result.success(null);

}
}
