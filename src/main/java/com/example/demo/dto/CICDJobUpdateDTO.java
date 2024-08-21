package com.example.demo.dto;

import lombok.Data;

@Data
public class CICDJobUpdateDTO {
    private String jobName;
    private String status;
    private String jobType;
}