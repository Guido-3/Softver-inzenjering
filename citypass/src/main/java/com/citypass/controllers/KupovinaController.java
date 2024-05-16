package com.citypass.controllers;

import com.citypass.models.Kupovina;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.KupovinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/kupovina")
public class KupovinaController {

    private final KupovinaService kupovinaService;

    public KupovinaController(KupovinaService kupovinaService) {
        this.kupovinaService = kupovinaService;
    }

    @GetMapping
    public List<Kupovina> getKupovina() {
        return kupovinaService.getKupovina();
    }

    @GetMapping(value = "/{id}")
    public Kupovina getKupovinaById(@PathVariable("id") int kupovinaId) {
        return kupovinaService.getKupovinaById(kupovinaId);
    }

    @PostMapping
    public DBOperationResponse addKupovina(@RequestBody Kupovina kupovina) {
        return kupovinaService.addKupovina(kupovina);
    }

    @PutMapping(value = "/{id}")
    public DBOperationResponse editKupovina(@PathVariable("id") int kupovinaId, @RequestBody Kupovina kupovina) {
        return kupovinaService.editKupovina(kupovinaId, kupovina);
    }

    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteKupovina(@PathVariable("id") int kupovinaId) {
        return kupovinaService.deleteKupovina(kupovinaId);
    }
}
