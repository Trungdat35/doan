package com.example.restfulapi.model;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "temperature_data")
@Data
public class TemperatureData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperature;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
}
