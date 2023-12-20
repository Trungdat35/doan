package com.example.restfulapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "summary_data")
@Data
public class SummaryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private int summaryID;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne()
    @JoinColumn(name = "humidity_id",foreignKey = @ForeignKey(name = "fk_sum_hum"))
    private HumidityData humidity;
    @ManyToOne()
    @JoinColumn(name = "temperature_id",foreignKey = @ForeignKey(name = "fk_sum_temp"))
    private TemperatureData temperature;
    @ManyToOne()
    @JoinColumn(name = "windspeed_id",foreignKey = @ForeignKey(name = "fk_sum_wind"))
    private WindSpeedData windspeed;
}
