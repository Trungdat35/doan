package com.example.restfulapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "temperature_data")
@Data
public class TemperatureData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temperature_id")
    private int temperatureID;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
    @OneToMany(mappedBy = "temperature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<SummaryData> summaryDataSet;
}
