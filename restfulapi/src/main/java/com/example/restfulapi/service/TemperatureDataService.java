package com.example.restfulapi.service;

import com.example.restfulapi.model.HourlyAverageTemperature;
import com.example.restfulapi.model.TemperatureData;
import com.example.restfulapi.repository.HourlyAverageTemperatureRepository;
import com.example.restfulapi.repository.TemperatureDataRepository;
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
    private HourlyAverageTemperatureRepository hourlyAverageTemperatureRepository;

    public void insertTemperatureData(int year, int month, int day) {
        LocalDateTime currentTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime endTime = currentTime.plusDays(1);
        Random random = new Random();

        while (currentTime.isBefore(endTime)) {
            TemperatureData data = new TemperatureData();
            data.setTemperature(18 + random.nextDouble() * (30 - 18)); // Giả định nhiệt độ từ 18 đến 30 độ C
            data.setRecordedAt(currentTime);
            temperatureDataRepository.save(data);

            currentTime = currentTime.plusMinutes(10);
        }
    }

    private int adjustHour(int hour) {
        // Trừ đi 1 đơn vị từ giá trị hour, ngoại trừ trường hợp hour = 0
        return (hour == 0) ? 23 : (hour - 1);
    }

    public void saveAverageTemperatureForHour(String startHour, String endHour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(startHour, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(endHour, formatter);
        double hourlyAverages = temperatureDataRepository.findAverageTemperatureByHour(dateTime, dateTime2);
        DecimalFormat df = new DecimalFormat("#.#");
        String formattedHourlyAverages = df.format(hourlyAverages);

        HourlyAverageTemperature hourlyAverageTemperature = new HourlyAverageTemperature();
        LocalDate localDate = dateTime.toLocalDate();
        hourlyAverageTemperature.setDate(localDate);
        int hour = dateTime.getHour();
        int adjustedHour = adjustHour(hour);

        if (hourlyAverageTemperatureRepository.findByDate(localDate) == 0) {
            hourlyAverageTemperature = createHourlyTemperature(hourlyAverageTemperature, formattedHourlyAverages, adjustedHour);
        } else {
            hourlyAverageTemperature = hourlyAverageTemperatureRepository.findByAvgDate(localDate);
            hourlyAverageTemperature = createHourlyTemperature(hourlyAverageTemperature, formattedHourlyAverages, adjustedHour);
        }

        hourlyAverageTemperatureRepository.save(hourlyAverageTemperature);
    }

    private HourlyAverageTemperature createHourlyTemperature(HourlyAverageTemperature hourlyAverageTemperature, String formattedHourlyAverages, int hour) {
        switch (hour) {
            case 0:
                hourlyAverageTemperature.setHour0(formattedHourlyAverages);
                break;
            case 1:
                hourlyAverageTemperature.setHour1(formattedHourlyAverages);
                break;
            case 2:
                hourlyAverageTemperature.setHour2(formattedHourlyAverages);
                break;
            case 3:
                hourlyAverageTemperature.setHour3(formattedHourlyAverages);
                break;
            case 4:
                hourlyAverageTemperature.setHour4(formattedHourlyAverages);
                break;
            case 5:
                hourlyAverageTemperature.setHour5(formattedHourlyAverages);
                break;
            case 6:
                hourlyAverageTemperature.setHour6(formattedHourlyAverages);
                break;
            case 7:
                hourlyAverageTemperature.setHour7(formattedHourlyAverages);
                break;
            case 8:
                hourlyAverageTemperature.setHour8(formattedHourlyAverages);
                break;
            case 9:
                hourlyAverageTemperature.setHour9(formattedHourlyAverages);
                break;
            case 10:
                hourlyAverageTemperature.setHour10(formattedHourlyAverages);
                break;
            case 11:
                hourlyAverageTemperature.setHour11(formattedHourlyAverages);
                break;
            case 12:
                hourlyAverageTemperature.setHour12(formattedHourlyAverages);
                break;
            case 13:
                hourlyAverageTemperature.setHour13(formattedHourlyAverages);
                break;
            case 14:
                hourlyAverageTemperature.setHour14(formattedHourlyAverages);
                break;
            case 15:
                hourlyAverageTemperature.setHour15(formattedHourlyAverages);
                break;
            case 16:
                hourlyAverageTemperature.setHour16(formattedHourlyAverages);
                break;
            case 17:
                hourlyAverageTemperature.setHour17(formattedHourlyAverages);
                break;
            case 18:
                hourlyAverageTemperature.setHour18(formattedHourlyAverages);
                break;
            case 19:
                hourlyAverageTemperature.setHour19(formattedHourlyAverages);
                break;
            case 20:
                hourlyAverageTemperature.setHour20(formattedHourlyAverages);
                break;
            case 21:
                hourlyAverageTemperature.setHour21(formattedHourlyAverages);
                break;
            case 22:
                hourlyAverageTemperature.setHour22(formattedHourlyAverages);
                break;
            case 23:
                hourlyAverageTemperature.setHour23(formattedHourlyAverages);
                break;
            default:
                break;
        }

        return hourlyAverageTemperature;
    }

    public List<TemperatureData> showList() {
        return temperatureDataRepository.findAll();
    }

    public HourlyAverageTemperature showAvg(LocalDate localDate) {
        return hourlyAverageTemperatureRepository.findByAvgDate(localDate);
    }

    public String getAvgNow() {
        LocalDateTime time = LocalDateTime.now();
        LocalDate localDate = time.toLocalDate();
        HourlyAverageTemperature temperature = hourlyAverageTemperatureRepository.findByAvgDate(localDate);
        int hour = time.getHour() -1;

        switch (hour) {
            case 0:
                return temperature.getHour0();

            case 1:
                return temperature.getHour1();

            case 2:
                return temperature.getHour2();

            case 3:
                return temperature.getHour3();

            case 4:
                return temperature.getHour4();

            case 5:
                return temperature.getHour5();

            case 6:
                return temperature.getHour6();

            case 7:
                return temperature.getHour7();

            case 8:
                return temperature.getHour8();

            case 9:
                return temperature.getHour9();

            case 10:
                return temperature.getHour10();

            case 11:
                return temperature.getHour11();

            case 12:
                return temperature.getHour12();

            case 13:
                return temperature.getHour13();

            case 14:
                return temperature.getHour14();

            case 15:
                return temperature.getHour15();

            case 16:
                return temperature.getHour16();

            case 17:
                return temperature.getHour17();

            case 18:
                return temperature.getHour18();

            case 19:
                return temperature.getHour18();

            case 20:
                return temperature.getHour19();

            case 21:
                return temperature.getHour20();

            case 22:
                return temperature.getHour22();

            case 23:
                return temperature.getHour23();
            default:
                break;
        }
        return null;
    }
}
