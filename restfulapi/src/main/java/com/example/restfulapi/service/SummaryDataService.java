package com.example.restfulapi.service;

import com.example.restfulapi.dto.ViewData;
import com.example.restfulapi.model.HumidityData;
import com.example.restfulapi.model.SummaryData;
import com.example.restfulapi.model.TemperatureData;
import com.example.restfulapi.model.WindSpeedData;
import com.example.restfulapi.repository.HumidityDataRepository;
import com.example.restfulapi.repository.SummaryDataRepository;
import com.example.restfulapi.repository.TemperatureDataRepository;
import com.example.restfulapi.repository.WindSpeedDataRepository;
import com.example.restfulapi.util.EmailUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SummaryDataService {
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private SummaryDataRepository summaryDataRepository;
    @Autowired
    private HumidityDataRepository humidityDataRepository;
    @Autowired
    private WindSpeedDataRepository windSpeedDataRepository;
    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    public void createSummaryData(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateFormat = LocalDateTime.parse(dateTime, formatter);
        TemperatureData temperatureData = temperatureDataRepository.findByRecordedAt(dateFormat);
        HumidityData humidityData = humidityDataRepository.findByRecordedAt(dateFormat);
        WindSpeedData windSpeedData = windSpeedDataRepository.findByRecordedAt(dateFormat);
        SummaryData summaryDataq = new SummaryData();
        summaryDataq.setTemperature(temperatureData);
        summaryDataq.setWindspeed(windSpeedData);
        summaryDataq.setHumidity(humidityData);
        summaryDataq.setDateTime(dateFormat);
        summaryDataRepository.save(summaryDataq);
    }
    public List<ViewData> getAllSummaryData() {
        List<SummaryData> listSummaryData = summaryDataRepository.findAll();
        List<ViewData> list = new ArrayList<>();
        for (SummaryData summaryData : listSummaryData) {
            ViewData dto = new ViewData();
            dto.setRecordedAt(summaryData.getDateTime());
            dto.setTemperature(summaryData.getTemperature().getTemperature());
            dto.setHumidity(summaryData.getHumidity().getHumidity());
            dto.setWindSpeed(summaryData.getWindspeed().getWindspeed());
            list.add(dto);
        }
        return list;
    }
    public List<ViewData> getSummaryDataByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<SummaryData> listSummaryData = summaryDataRepository.findByDateTimeBetween(startOfDay, endOfDay);
        List<ViewData> viewDataList = new ArrayList<>();

        for (SummaryData summaryData : listSummaryData) {
            ViewData dto = new ViewData();
            dto.setRecordedAt(summaryData.getDateTime());
            dto.setTemperature(summaryData.getTemperature().getTemperature());
            dto.setHumidity(summaryData.getHumidity().getHumidity());
            dto.setWindSpeed(summaryData.getWindspeed().getWindspeed());
            viewDataList.add(dto);
        }

        return viewDataList;
    }
    public ViewData getSummaryNow(){
        SummaryData summaryData = summaryDataRepository.findFirstByOrderByDateTimeDesc();
        ViewData viewData = new ViewData();
        viewData.setHumidity(summaryData.getHumidity().getHumidity());
        viewData.setRecordedAt(summaryData.getDateTime());
        viewData.setTemperature(summaryData.getTemperature().getTemperature());
        viewData.setWindSpeed(summaryData.getWindspeed().getWindspeed());
        if(viewData.getHumidity() > 75.00 || viewData.getTemperature() > 20.00){
            try {
                emailUtil.sendNotification(viewData.getRecordedAt(),
                        viewData.getTemperature()
                        ,viewData.getHumidity(),
                        "Nhiệt độ hoặc độ ẩm vượt mức cho phép !",
                        "dotrungdat.mda@gmail.com"
                        );
            } catch (MessagingException e) {
                System.out.println("exception");
            }
        }
        return viewData;
    }
}
