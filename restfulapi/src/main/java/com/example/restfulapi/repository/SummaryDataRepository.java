package com.example.restfulapi.repository;

import com.example.restfulapi.model.SummaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SummaryDataRepository extends JpaRepository<SummaryData,Integer> {
    @Query(value = "SELECT * FROM summary_data WHERE date = :datet",nativeQuery = true)
    SummaryData findByAvgDate(@Param("datet") LocalDate datet);
    @Query(value = "SELECT COUNT(*) FROM summary_data WHERE date = :date",nativeQuery = true)
    int findByDate(@Param("date")LocalDate date);
    SummaryData findFirstByOrderByDateTimeDesc();
    List<SummaryData> findByDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
