package com.example.restfulapi.repository;

import com.example.restfulapi.model.TemperatureData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureDataRepository extends JpaRepository<TemperatureData,Integer> {
    @Query(value = "SELECT AVG(temperature) as avgt " +
            "FROM temperature_data AS t " +
            "WHERE t.recorded_at BETWEEN :startHour AND :endHour", nativeQuery = true)
    double findAverageTemperatureByHour(@Param("startHour") LocalDateTime startHour,
                                        @Param("endHour") LocalDateTime endHour);

    @Query(value = "SELECT COUNT(*) FROM temperature_data WHERE recorded_at = :date",nativeQuery = true)
    int findByDate(@Param("date")LocalDate date);
}
