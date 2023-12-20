package com.example.restfulapi.controller;

import com.example.restfulapi.dto.Response;
import com.example.restfulapi.dto.ViewData;
import com.example.restfulapi.model.SummaryData;
import com.example.restfulapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class SummaryDataController {
    @Autowired
    private TemperatureDataService temperatureDataService;
    @Autowired
    private HumidityDataService humidityDataService;
    @Autowired
    private WindSpeedDataService windSpeedDataService;
    @Autowired
    private SummaryDataService summaryDataService;

    @PostMapping(value = "/createtemperaturedata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertTemperatureData(@RequestParam("year") int year,
                                                          @RequestParam("month") int month,
                                                          @RequestParam("day") int day) {
        temperatureDataService.createTemperatureData(year, month, day);
        return ResponseEntity.ok(new Response(HttpStatus.OK, "Thành công !"));
    }

    @PostMapping(value = "/createhumiditydata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertHumidityData(@RequestParam("year") int year,
                                                       @RequestParam("month") int month,
                                                       @RequestParam("day") int day) {
        humidityDataService.createHumidityData(year, month, day);
        return ResponseEntity.ok(new Response(HttpStatus.OK, "Thành công !"));
    }

    @PostMapping(value = "/createwindspeeddata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertWindSpeedData(@RequestParam("year") int year,
                                                        @RequestParam("month") int month,
                                                        @RequestParam("day") int day) {
        windSpeedDataService.createWindSpeedData(year, month, day);
        return ResponseEntity.ok(new Response(HttpStatus.OK, "Thành công !"));
    }

    @PostMapping(value = "/createsummarydata", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> insertSummaryData(@RequestParam("datetime") String dateTime) {
        summaryDataService.createSummaryData(dateTime);
        return ResponseEntity.ok(new Response(HttpStatus.OK, "Thành công !"));
    }

    @GetMapping(value = "/getsummarydatabydate")
    public List<ViewData> getSummaryByDate(@RequestParam("date") LocalDate date) {
        return summaryDataService.getSummaryDataByDate(date);
    }
    @GetMapping(value = "/getsummarydatanow")
    public ViewData getSummaryNow() {
        return summaryDataService.getSummaryNow();
    }
//    @PostMapping(value = "/saveaveragetemperaturebyhour", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> saveAverageTemperatureByHour(
//            @RequestParam("startHour") String startHour,
//            @RequestParam("endHour") String endHour) {
//        temperatureDataService.saveAverageTemperatureForHour(startHour, endHour);
//        return ResponseEntity.ok(new Response(HttpStatus.OK, "Thành công !"));
//    }
//
//    @GetMapping(value = "/gettempbyhour")
//    public String getTempByHour() {
//        return temperatureDataService.getAvgNowTemp();
//    }
//
//    @GetMapping(value = "/gettempbydate")
//    public SummaryData getTempByHour(@RequestParam("date") LocalDate date) {
//        return temperatureDataService.getAllAvgTemp(date);
//    }

}
