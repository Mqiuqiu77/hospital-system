package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.dto.HospitalUpdateDTO;
import com.hospital.service.HospitalService;
import com.hospital.vo.HospitalVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService){
        this.hospitalService = hospitalService;
    }

    @GetMapping("/list")
    public Result<List<HospitalVO>> list(){
        List<HospitalVO> hospitals =
                hospitalService.listHospitals();

        return Result.success(hospitals);
    }

    @PutMapping("/{id}")
    public Result<Void> updates(@PathVariable Long id,
                                @Valid @RequestBody HospitalUpdateDTO hospitalUpdateDTO){
        hospitalService.updateHospital(id,hospitalUpdateDTO);
        return Result.success(null);
    }

}
