package com.citypass.controllers;

import com.citypass.models.Turista;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.TuristaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/turista")
@CrossOrigin(origins = "http://localhost:4200")
public class TuristaController {

    private final TuristaService turistaService;

    public TuristaController(TuristaService turistaService) {
        this.turistaService = turistaService;
    }

    @GetMapping
    public List<Turista> getTurista() {
        return turistaService.getTurista();
    }

    @GetMapping(value = "/{id}")
    public Turista getTuristaById(@PathVariable("id") int turistaId) {
        return turistaService.getTuristaById(turistaId);
    }

    @PostMapping
    public DBOperationResponse addTurista(@RequestBody Turista turista) {
        return turistaService.addTurista(turista);
    }

    @PutMapping(value = "/{id}")
    public DBOperationResponse editTurista(@PathVariable("id") int turistaId, @RequestBody Turista turista) {
        return turistaService.editTurista(turistaId, turista);
    }

    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteTurista(@PathVariable("id") int turistaId) {
        return turistaService.deleteTurista(turistaId);
    }
}