package com.example.restfulapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewData {
    private LocalDateTime recordedAt;
    private Double temperature;
    private Double humidity;
    private Double windSpeed;
}
