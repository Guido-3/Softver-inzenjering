package com.citypass.services;

import com.citypass.models.Drzava;
import com.citypass.repositories.DrzavaRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrzavaService {

    private final DrzavaRepository drzavaRepository;

    public DrzavaService(DrzavaRepository _drzavaRepository) {
        this.drzavaRepository = _drzavaRepository;
    }

    public List<Drzava> getDrzava() {
        return this.drzavaRepository.getDrzava();
    }

    public Drzava getDrzavaByIme(String drzavaIme) {return this.drzavaRepository.getDrzavaByIme(drzavaIme);}

    public DBOperationResponse addDrzava(Drzava d) {
        return this.drzavaRepository.addDrzava(d);
    }

    public DBOperationResponse editDrzava(String drzavaIme, Drzava d) {
        return this.drzavaRepository.editDrzava(drzavaIme, d);
    }

    public DBOperationResponse deleteDrzava(String drzavaIme) {
        return this.drzavaRepository.deleteDrzava(drzavaIme);
    }
}
