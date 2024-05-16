package com.citypass.services;

import com.citypass.models.DailyPass;
import com.citypass.repositories.DailyPassRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyPassService {

    private final DailyPassRepository dailyPassRepository;

    public DailyPassService(DailyPassRepository dailyPassRepository) {
        this.dailyPassRepository = dailyPassRepository;
    }

    public List<DailyPass> getDailyPasses() {
        return dailyPassRepository.getDailyPasses();
    }

    public DailyPass getDailyPassById(int dailyPassId) {
        return dailyPassRepository.getDailyPassById(dailyPassId);
    }

    public DBOperationResponse addDailyPass(DailyPass dailyPass) {
        return dailyPassRepository.addDailyPass(dailyPass);
    }

    public DBOperationResponse editDailyPass(int dailyPassId, DailyPass dailyPass) {
        return dailyPassRepository.editDailyPass(dailyPassId, dailyPass);
    }

    public DBOperationResponse deleteDailyPass(int dailyPassId) {
        return dailyPassRepository.deleteDailyPass(dailyPassId);
    }
}
