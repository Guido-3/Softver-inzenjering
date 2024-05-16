package com.citypass.models;

public class Kupovina {
    private int id;
    private String datum;
    private int korisnik_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getKorisnik_id() {
        return korisnik_id;
    }

    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    public Kupovina(int id, String datum, int korisnik_id) {
        this.id = id;
        this.datum = datum;
        this.korisnik_id = korisnik_id;
    }

    public Kupovina() {
    }
}
