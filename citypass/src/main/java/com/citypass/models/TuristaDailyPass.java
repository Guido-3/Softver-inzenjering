package com.citypass.models;

import java.util.Date;

public class TuristaDailyPass {
    private int turistaId;
    private int dailyPassId;
    private Date datumOd;
    private Date datumDo;
    private double ukupnaCijena;
    private int kupovinaId;

    public TuristaDailyPass() {
    }

    public TuristaDailyPass(int turistaId, int dailyPassId, Date datumOd, Date datumDo, double ukupnaCijena, int kupovinaId) {
        this.turistaId = turistaId;
        this.dailyPassId = dailyPassId;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupnaCijena = ukupnaCijena;
        this.kupovinaId = kupovinaId;
    }

    public int getTuristaId() {
        return turistaId;
    }

    public void setTuristaId(int turistaId) {
        this.turistaId = turistaId;
    }

    public int getDailyPassId() {
        return dailyPassId;
    }

    public void setDailyPassId(int dailyPassId) {
        this.dailyPassId = dailyPassId;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(double ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public int getKupovinaId() {
        return kupovinaId;
    }

    public void setKupovinaId(int kupovinaId) {
        this.kupovinaId = kupovinaId;
    }
}
