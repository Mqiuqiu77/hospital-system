package com.hospital.vo;

import lombok.Data;

@Data
public class HospitalVO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String hospitalLevel;
}
