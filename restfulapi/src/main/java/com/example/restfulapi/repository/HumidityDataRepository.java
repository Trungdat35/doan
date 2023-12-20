package com.example.restfulapi.repository;

import com.example.restfulapi.model.HumidityData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HumidityDataRepository extends JpaRepository<HumidityData,Integer> {
    HumidityData findByRecordedAt(LocalDateTime dateTime);
}
