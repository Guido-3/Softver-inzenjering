package com.citypass.controllers;

import com.citypass.models.TuristaDailyPass;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.TuristaDailyPassService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/turista-daily-pass")
@CrossOrigin(origins = "http://localhost:4200")
public class TuristaDailyPassController {

    private final TuristaDailyPassService turistaDailyPassService;

    public TuristaDailyPassController(TuristaDailyPassService turistaDailyPassService) {
        this.turistaDailyPassService = turistaDailyPassService;
    }

    @GetMapping
    public List<TuristaDailyPass> getTuristaDailyPass() {
        return turistaDailyPassService.getTuristaDailyPass();
    }

    @GetMapping(value = "/{turistaId}/{dailyPassId}")
    public TuristaDailyPass getTuristaDailyPassById(@PathVariable("turistaId") int turistaId, @PathVariable("dailyPassId") int dailyPassId) {
        return turistaDailyPassService.getTuristaDailyPassById(turistaId, dailyPassId);
    }

    @PostMapping
    public DBOperationResponse addTuristaDailyPass(@RequestBody TuristaDailyPass turistaDailyPass) {
        return turistaDailyPassService.addTuristaDailyPass(turistaDailyPass);
    }

    @PutMapping(value = "/{turistaId}/{dailyPassId}")
    public DBOperationResponse editTuristaDailyPass(@PathVariable("turistaId") int turistaId, @PathVariable("dailyPassId") int dailyPassId, @RequestBody TuristaDailyPass turistaDailyPass) {
        return turistaDailyPassService.editTuristaDailyPass(turistaId, dailyPassId, turistaDailyPass);
    }

    @DeleteMapping(value = "/{turistaId}/{dailyPassId}")
    public DBOperationResponse deleteTuristaDailyPass(@PathVariable("turistaId") int turistaId, @PathVariable("dailyPassId") int dailyPassId) {
        return turistaDailyPassService.deleteTuristaDailyPass(turistaId, dailyPassId);
    }
}
