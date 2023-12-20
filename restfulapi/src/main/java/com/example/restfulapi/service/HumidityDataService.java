package com.example.restfulapi.service;

import com.example.restfulapi.model.HumidityData;
import com.example.restfulapi.repository.HumidityDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class HumidityDataService {
    @Autowired
    private HumidityDataRepository humidityDataRepository;
    public void createHumidityData(int year, int month, int day) {
        LocalDateTime currentTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime endTime = currentTime.plusDays(1);
        Random random = new Random();

        while (currentTime.isBefore(endTime)) {
            HumidityData data = new HumidityData();
            data.setHumidity(40 + random.nextDouble() * (80 - 40)); // Giả định độ ẩm từ 40% đến 80%
            data.setRecordedAt(currentTime);
            humidityDataRepository.save(data);

            currentTime = currentTime.plusHours(1);
        }
    }
}
