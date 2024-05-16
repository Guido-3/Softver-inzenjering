package com.citypass.services;

import com.citypass.models.Znamenitost;
import com.citypass.repositories.ZnamenitostRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZnamenitostService {

    private final ZnamenitostRepository znamenitostRepository;

    public ZnamenitostService(ZnamenitostRepository _znamenitostRepository) {
        this.znamenitostRepository = _znamenitostRepository;
    }

    public List<Znamenitost> getZnamenitost() {
        return this.znamenitostRepository.getZnamenitost();
    }

    public Znamenitost getZnamenitostById(int znamenitostId) {return this.znamenitostRepository.getZnamenitostById(znamenitostId);}

    public DBOperationResponse addZnamenitost(Znamenitost z) {
        return this.znamenitostRepository.addZnamenitost(z);
    }

    public DBOperationResponse editZnamenitost(int znamenitostId, Znamenitost z) {
        return this.znamenitostRepository.editZnamenitost(znamenitostId, z);
    }

    public DBOperationResponse deleteZnamenitost(int znamenitostId) {
        return this.znamenitostRepository.deleteZnamenitost(znamenitostId);
    }
}
