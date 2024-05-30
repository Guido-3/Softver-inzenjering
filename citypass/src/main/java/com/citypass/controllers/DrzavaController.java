package com.citypass.controllers;

import com.citypass.models.Drzava;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.DrzavaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/drzava")
@CrossOrigin(origins = "http://localhost:4200")
public class DrzavaController {
    private final DrzavaService drzavaService;

    public DrzavaController(DrzavaService _drzavaService) {
        this.drzavaService = _drzavaService;
    }

    @GetMapping
    public List<Drzava> getDrzava(){
        return this.drzavaService.getDrzava();
    }

    @GetMapping(value = "/{ime}")
    public Drzava getDrzavaByIme(@PathVariable("ime") String drzavaIme){
        return this.drzavaService.getDrzavaByIme(drzavaIme);
    }

    @PostMapping
    public DBOperationResponse addDrzava(@RequestBody Drzava d){return this.drzavaService.addDrzava(d);}

    @PutMapping(value = "/{ime}")
    public DBOperationResponse editDrzava(@PathVariable("ime") String drzavaIme, @RequestBody Drzava d){
        return this.drzavaService.editDrzava(drzavaIme, d);
    }

    @DeleteMapping(value = "/{ime}")
    public DBOperationResponse deleteDrzava(@PathVariable("ime") String drzavaIme){
        return this.drzavaService.deleteDrzava(drzavaIme);
    }

}


