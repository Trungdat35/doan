package com.example.restfulapi.service;

import com.example.restfulapi.model.WindSpeedData;
import com.example.restfulapi.repository.WindSpeedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class WindSpeedDataService {
    @Autowired
    private WindSpeedDataRepository windSpeedDataRepository;
    public void createWindSpeedData(int year, int month, int day) {
        LocalDateTime currentTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime endTime = currentTime.plusDays(1);
        Random random = new Random();

        while (currentTime.isBefore(endTime)) {
            WindSpeedData data = new WindSpeedData();
            data.setWindspeed(5 + random.nextDouble() * (20 - 5)); // Giả định tốc độ gió từ 5 m/s đến 20 m/s
            data.setRecordedAt(currentTime);
            windSpeedDataRepository.save(data);
            currentTime = currentTime.plusHours(1);
        }
    }
}
