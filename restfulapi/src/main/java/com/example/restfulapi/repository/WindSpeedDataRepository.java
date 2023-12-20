package com.example.restfulapi.repository;

import com.example.restfulapi.model.TemperatureData;
import com.example.restfulapi.model.WindSpeedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface WindSpeedDataRepository extends JpaRepository<WindSpeedData,Integer> {
    WindSpeedData findByRecordedAt(LocalDateTime dateTime);
}
