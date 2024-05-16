package com.citypass.controllers;

import com.citypass.models.Znamenitost;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.ZnamenitostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/znamenitost")
public class ZnamenitostController {

    private final ZnamenitostService znamenitostService;

    public ZnamenitostController(ZnamenitostService _znamenitostService) {
        this.znamenitostService = _znamenitostService;
    }

    @GetMapping
    public List<Znamenitost> getZnamenitost(){
        return this.znamenitostService.getZnamenitost();
    }

    @GetMapping(value = "/{id}")
    public Znamenitost getZnamenitostById(@PathVariable("id") int znamenitosId){
        return this.znamenitostService.getZnamenitostById(znamenitosId);
    }

    @PostMapping
    public DBOperationResponse addZnamenitost(@RequestBody Znamenitost z){return this.znamenitostService.addZnamenitost(z);}

    @PutMapping(value = "/{id}")
    public DBOperationResponse editZnamenitost(@PathVariable("id") int znamenitostId, @RequestBody Znamenitost z){
        return this.znamenitostService.editZnamenitost(znamenitostId, z);
    }

    @DeleteMapping(value = "/{id}")
    public DBOperationResponse deleteZnamenitost(@PathVariable("id") int znamenitostId){
        return this.znamenitostService.deleteZnamenitost(znamenitostId);
    }

}
