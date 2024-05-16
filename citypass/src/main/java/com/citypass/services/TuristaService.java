package com.citypass.services;

import com.citypass.models.Turista;
import com.citypass.repositories.TuristaRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuristaService {

    private final TuristaRepository turistaRepository;

    public TuristaService(TuristaRepository turistaRepository) {
        this.turistaRepository = turistaRepository;
    }

    public List<Turista> getTurista() {
        return turistaRepository.getTurista();
    }

    public Turista getTuristaById(int turistaId) {
        return turistaRepository.getTuristaById(turistaId);
    }

    public DBOperationResponse addTurista(Turista turista) {
        return turistaRepository.addTurista(turista);
    }

    public DBOperationResponse editTurista(int turistaId, Turista turista) {
        return turistaRepository.editTurista(turistaId, turista);
    }

    public DBOperationResponse deleteTurista(int turistaId) {
        return turistaRepository.deleteTurista(turistaId);
    }
}