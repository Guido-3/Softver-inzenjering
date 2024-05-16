package com.citypass.services;

import com.citypass.models.Korisnik;
import com.citypass.repositories.KorisnikRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;

    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public List<Korisnik> getKorisnici() {
        return this.korisnikRepository.getKorisnik();
    }

    public Korisnik getKorisnikById(int korisnikId) {
        return this.korisnikRepository.getKorisnikById(korisnikId);
    }

    public DBOperationResponse addKorisnik(Korisnik korisnik) {
        return this.korisnikRepository.addKorisnik(korisnik);
    }

    public DBOperationResponse editKorisnik(int korisnikId, Korisnik korisnik) {
        return this.korisnikRepository.editKorisnik(korisnikId, korisnik);
    }

    public DBOperationResponse deleteKorisnik(int korisnikId) {
        return this.korisnikRepository.deleteKorisnik(korisnikId);
    }
}
