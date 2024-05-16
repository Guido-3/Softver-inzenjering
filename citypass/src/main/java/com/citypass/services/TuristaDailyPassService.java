package com.citypass.services;

import com.citypass.models.Kupovina;
import com.citypass.models.TuristaDailyPass;
import com.citypass.repositories.TuristaDailyPassRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuristaDailyPassService {

    private final TuristaDailyPassRepository turistaDailyPassRepository;

    public TuristaDailyPassService(TuristaDailyPassRepository turistaDailyPassRepository) {
        this.turistaDailyPassRepository = turistaDailyPassRepository;
    }
    public List<TuristaDailyPass> getTuristaDailyPass() {
        return this.turistaDailyPassRepository.getTuristaDailyPass();
    }

    public TuristaDailyPass getTuristaDailyPassById(int turistaId, int dailyPassId) {
        return turistaDailyPassRepository.getTuristaDailyPassById(turistaId, dailyPassId);
    }

    public DBOperationResponse addTuristaDailyPass(TuristaDailyPass turistaDailyPass) {
        return turistaDailyPassRepository.addTuristaDailyPass(turistaDailyPass);
    }

    public DBOperationResponse editTuristaDailyPass(int turistaId, int dailyPassId, TuristaDailyPass turistaDailyPass) {
        return turistaDailyPassRepository.editTuristaDailyPass(turistaId, dailyPassId, turistaDailyPass);
    }

    public DBOperationResponse deleteTuristaDailyPass(int turistaId, int dailyPassId) {
        return turistaDailyPassRepository.deleteTuristaDailyPass(turistaId, dailyPassId);
    }
}
