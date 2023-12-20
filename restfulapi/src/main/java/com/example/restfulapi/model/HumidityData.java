package com.example.restfulapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "humidity_data")
@Data
public class HumidityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "humidity_id")
    private int temperatureID;
    @Column(name = "humidity")
    private Double humidity;
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
    @OneToMany(mappedBy = "humidity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<SummaryData> summaryDataSet;
}
