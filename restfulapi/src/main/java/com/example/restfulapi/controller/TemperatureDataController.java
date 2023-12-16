package com.example.restfulapi.controller;

import com.example.restfulapi.model.HourlyAverageTemperature;
import com.example.restfulapi.model.TemperatureData;
import com.example.restfulapi.service.TemperatureDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class TemperatureDataController {
    @Autowired
    private TemperatureDataService temperatureDataService;

    @PostMapping(value = "/inserttemperaturedata",produces =  MediaType.APPLICATION_JSON_VALUE)
    public void insertTemperatureData(@RequestParam("year") int year,
    @RequestParam("month") int month,
    @RequestParam("day") int day) {
        temperatureDataService.insertTemperatureData(year,month,day);
    }

    @PostMapping(value = "/saveaveragetemperaturebyhour",produces =  MediaType.APPLICATION_JSON_VALUE)
    public void saveAverageTemperatureByHour(
            @RequestParam("startHour") String startHour,
            @RequestParam("endHour") String endHour) {
         temperatureDataService.saveAverageTemperatureForHour(startHour, endHour);
    }

    @GetMapping(value = "/gettempbyhour")
    public String getTempByHour() {
        return temperatureDataService.getAvgNow();
    }
    @GetMapping(value = "/gettempbydate")
    public HourlyAverageTemperature getTempByHour(@RequestParam("date") LocalDate date) {
        return temperatureDataService.showAvg(date);
    }

//    @PostMapping(value = "/savetempbyhour", produces = MediaType.APPLICATION_JSON_VALUE)
//    public void saveTempByHour() {
//      temperatureDataService.saveHourlyAverageTemperature();
//    }
}
