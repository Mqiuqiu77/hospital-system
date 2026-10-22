package com.hospital.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientVO {
    private Long id;
    private String patientNo;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String phone;
    private String address;
}
