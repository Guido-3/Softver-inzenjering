package com.citypass.controllers;

import com.citypass.models.DailyPass;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.DailyPassService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/daily-pass")
@CrossOrigin(origins = "http://localhost:4200")
public class DailyPassController {

    private final DailyPassService dailyPassService;

    public DailyPassController(DailyPassService dailyPassService) {
        this.dailyPassService = dailyPassService;
    }

    @GetMapping
    public List<DailyPass> getDailyPasses() {
        return dailyPassService.getDailyPasses();
    }

    @GetMapping(value = "/{id}")
    public DailyPass getDailyPassById(@PathVariable("id") int dailyPassId) {
        return dailyPassService.getDailyPassById(dailyPassId);
    }

    @GetMapping("price-per-day/{id}")
    public Double getCijenaDailyPassById(@PathVariable("id") int dailyPassId) {
        return dailyPassService.getCijenaDailyPassById(dailyPassId);
    }

    @PostMapping
    public DBOperationResponse addDailyPass(@RequestBody DailyPass dailyPass) {
        return dailyPassService.addDailyPass(dailyPass);
    }

    @PutMapping(value = "/{id}")
    public DBOperationResponse editDailyPass(@PathVariable("id") int dailyPassId, @RequestBody DailyPass dailyPass) {
        return dailyPassService.editDailyPass(dailyPassId, dailyPass);
    }

    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteDailyPass(@PathVariable("id") int dailyPassId) {
        return dailyPassService.deleteDailyPass(dailyPassId);
    }
}
