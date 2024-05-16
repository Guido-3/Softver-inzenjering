package com.citypass.services;

import com.citypass.models.Kupovina;
import com.citypass.repositories.KupovinaRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KupovinaService {

    private final KupovinaRepository kupovinaRepository;

    public KupovinaService(KupovinaRepository kupovinaRepository) {
        this.kupovinaRepository = kupovinaRepository;
    }

    public List<Kupovina> getKupovina() {
        return this.kupovinaRepository.getKupovina();
    }

    public Kupovina getKupovinaById(int kupovinaId) {
        return this.kupovinaRepository.getKupovinaById(kupovinaId);
    }

    public DBOperationResponse addKupovina(Kupovina kupovina) {
        return this.kupovinaRepository.addKupovina(kupovina);
    }

    public DBOperationResponse editKupovina(int kupovinaId, Kupovina kupovina) {
        return this.kupovinaRepository.editKupovina(kupovinaId, kupovina);
    }

    public DBOperationResponse deleteKupovina(int kupovinaId) {
        return this.kupovinaRepository.deleteKupovina(kupovinaId);
    }
}
