package com.example.restfulapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "windspeed_data")
@Data
public class WindSpeedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "windspeed_id")
    private int temperatureID;
    @Column(name = "windspeed")
    private Double windspeed;
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;
    @OneToMany(mappedBy = "windspeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<SummaryData> summaryDataSet;
}
