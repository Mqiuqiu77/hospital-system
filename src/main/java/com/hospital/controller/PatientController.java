package com.hospital.controller;

import com.hospital.dto.PatientQueryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @GetMapping("/{id}")
    public String patientInfo(@PathVariable Integer id){
        return "患者编号" + id;
    }

    @GetMapping("/search")
    public String search(PatientQueryDTO dto){
        return  "查询条件：" + '\n' + "姓名:" + dto.getName() + '\n'
        +"年龄：" + dto.getAge() + '\n' + "性别:" + dto.getGender();

    }
}
