package com.citypass.controllers;

import com.citypass.models.Korisnik;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.KorisnikService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/korisnik")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {

    private final KorisnikService korisnikService;

    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<Korisnik> getKorisnici() {
        return korisnikService.getKorisnici();
    }

    @GetMapping(value = "/{id}")
    public Korisnik getKorisnikById(@PathVariable("id") int korisnikId) {
        return korisnikService.getKorisnikById(korisnikId);
    }

    @PostMapping
    public DBOperationResponse addKorisnik(@RequestBody Korisnik korisnik) {
        return korisnikService.addKorisnik(korisnik);
    }

    @PutMapping(value = "/{id}")
    public DBOperationResponse editKorisnik(@PathVariable("id") int korisnikId, @RequestBody Korisnik korisnik) {
        return korisnikService.editKorisnik(korisnikId, korisnik);
    }

    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteKorisnik(@PathVariable("id") int korisnikId) {
        return korisnikService.deleteKorisnik(korisnikId);
    }
}
