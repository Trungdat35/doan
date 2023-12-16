package com.example.restfulapi.repository;

import com.example.restfulapi.model.HourlyAverageTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HourlyAverageTemperatureRepository extends JpaRepository<HourlyAverageTemperature,Integer> {
    @Query(value = "SELECT * FROM hourly_average_temperature WHERE date = :datet",nativeQuery = true)
    HourlyAverageTemperature findByAvgDate(@Param("datet") LocalDate datet);
    @Query(value = "SELECT COUNT(*) FROM hourly_average_temperature WHERE date = :date",nativeQuery = true)
    int findByDate(@Param("date")LocalDate date);
}
