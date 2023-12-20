package com.example.restfulapi.service;

import com.example.restfulapi.model.*;
import com.example.restfulapi.model.SummaryData;
import com.example.restfulapi.repository.HumidityDataRepository;
import com.example.restfulapi.repository.SummaryDataRepository;
import com.example.restfulapi.repository.TemperatureDataRepository;
import com.example.restfulapi.repository.WindSpeedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TemperatureDataService {
    @Autowired
    private TemperatureDataRepository temperatureDataRepository;
    @Autowired
    private SummaryDataRepository summaryDataRepository;
    @Autowired
    private HumidityDataRepository humidityDataRepository;
    @Autowired
    private WindSpeedDataRepository windSpeedDataRepository;
    public void createTemperatureData(int year, int month, int day) {
        LocalDateTime currentTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime endTime = currentTime.plusDays(1);
        Random random = new Random();

        while (currentTime.isBefore(endTime)) {
            TemperatureData data = new TemperatureData();
            data.setTemperature(18 + random.nextDouble() * (30 - 18)); // Giả định nhiệt độ từ 18 đến 30 độ C
            data.setRecordedAt(currentTime);
            temperatureDataRepository.save(data);

            currentTime = currentTime.plusHours(1);
        }
    }
    private int adjustHour(int hour) {
        // Trừ đi 1 đơn vị từ giá trị hour, ngoại trừ trường hợp hour = 0
        return (hour == 0) ? 23 : (hour - 1);
    }

//    public void saveAverageTemperatureForHour(String startHour, String endHour) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(startHour, formatter);
//        LocalDateTime dateTime2 = LocalDateTime.parse(endHour, formatter);
//        double hourlyAverages = temperatureDataRepository.findAverageTemperatureByHour(dateTime, dateTime2);
//        DecimalFormat df = new DecimalFormat("#.#");
//        String formattedHourlyAverages = df.format(hourlyAverages);
//
//        SummaryData summaryData = new SummaryData();
//        LocalDate localDate = dateTime.toLocalDate();
//        summaryData.setDate(localDate);
//        int hour = dateTime.getHour();
//        int adjustedHour = adjustHour(hour);
//
//        if (summaryDataRepository.findByDate(localDate) == 0) {
//            summaryData = createHourlyTemperature(summaryData, formattedHourlyAverages, adjustedHour);
//        } else {
//            summaryData = summaryDataRepository.findByAvgDate(localDate);
//            summaryData = createHourlyTemperature(summaryData, formattedHourlyAverages, adjustedHour);
//        }
//
//        summaryDataRepository.save(summaryData);
//    }
//
//    private SummaryData createHourlyTemperature(SummaryData summaryData, String formattedHourlyAverages, int hour) {
//        switch (hour) {
//            case 0:
//                summaryData.setHour0(formattedHourlyAverages);
//                break;
//            case 1:
//                summaryData.setHour1(formattedHourlyAverages);
//                break;
//            case 2:
//                summaryData.setHour2(formattedHourlyAverages);
//                break;
//            case 3:
//                summaryData.setHour3(formattedHourlyAverages);
//                break;
//            case 4:
//                summaryData.setHour4(formattedHourlyAverages);
//                break;
//            case 5:
//                summaryData.setHour5(formattedHourlyAverages);
//                break;
//            case 6:
//                summaryData.setHour6(formattedHourlyAverages);
//                break;
//            case 7:
//                summaryData.setHour7(formattedHourlyAverages);
//                break;
//            case 8:
//                summaryData.setHour8(formattedHourlyAverages);
//                break;
//            case 9:
//                summaryData.setHour9(formattedHourlyAverages);
//                break;
//            case 10:
//                summaryData.setHour10(formattedHourlyAverages);
//                break;
//            case 11:
//                summaryData.setHour11(formattedHourlyAverages);
//                break;
//            case 12:
//                summaryData.setHour12(formattedHourlyAverages);
//                break;
//            case 13:
//                summaryData.setHour13(formattedHourlyAverages);
//                break;
//            case 14:
//                summaryData.setHour14(formattedHourlyAverages);
//                break;
//            case 15:
//                summaryData.setHour15(formattedHourlyAverages);
//                break;
//            case 16:
//                summaryData.setHour16(formattedHourlyAverages);
//                break;
//            case 17:
//                summaryData.setHour17(formattedHourlyAverages);
//                break;
//            case 18:
//                summaryData.setHour18(formattedHourlyAverages);
//                break;
//            case 19:
//                summaryData.setHour19(formattedHourlyAverages);
//                break;
//            case 20:
//                summaryData.setHour20(formattedHourlyAverages);
//                break;
//            case 21:
//                summaryData.setHour21(formattedHourlyAverages);
//                break;
//            case 22:
//                summaryData.setHour22(formattedHourlyAverages);
//                break;
//            case 23:
//                summaryData.setHour23(formattedHourlyAverages);
//                break;
//            default:
//                break;
//        }
//
//        return summaryData;
//    }
//
//    public List<TemperatureData> showList() {
//        return temperatureDataRepository.findAll();
//    }
//
//    public SummaryData getAllAvgTemp(LocalDate localDate) {
//        return summaryDataRepository.findByAvgDate(localDate);
//    }
//
//    public String getAvgNowTemp() {
//        LocalDateTime time = LocalDateTime.now();
//        LocalDate localDate = time.toLocalDate();
//        SummaryData temperature = summaryDataRepository.findByAvgDate(localDate);
//        int hour = time.getHour() - 1;
//
//        switch (hour) {
//            case 0:
//                return temperature.getHour0();
//
//            case 1:
//                return temperature.getHour1();
//
//            case 2:
//                return temperature.getHour2();
//
//            case 3:
//                return temperature.getHour3();
//
//            case 4:
//                return temperature.getHour4();
//
//            case 5:
//                return temperature.getHour5();
//
//            case 6:
//                return temperature.getHour6();
//
//            case 7:
//                return temperature.getHour7();
//
//            case 8:
//                return temperature.getHour8();
//
//            case 9:
//                return temperature.getHour9();
//
//            case 10:
//                return temperature.getHour10();
//
//            case 11:
//                return temperature.getHour11();
//
//            case 12:
//                return temperature.getHour12();
//
//            case 13:
//                return temperature.getHour13();
//
//            case 14:
//                return temperature.getHour14();
//
//            case 15:
//                return temperature.getHour15();
//
//            case 16:
//                return temperature.getHour16();
//
//            case 17:
//                return temperature.getHour17();
//
//            case 18:
//                return temperature.getHour18();
//
//            case 19:
//                return temperature.getHour18();
//
//            case 20:
//                return temperature.getHour19();
//
//            case 21:
//                return temperature.getHour20();
//
//            case 22:
//                return temperature.getHour22();
//
//            case 23:
//                return temperature.getHour23();
//            default:
//                break;
//        }
//        return null;
//    }
}
