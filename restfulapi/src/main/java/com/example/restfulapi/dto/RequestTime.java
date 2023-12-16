package com.example.restfulapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestTime {
    private LocalDateTime startHour;
    private LocalDateTime endHour;
}
