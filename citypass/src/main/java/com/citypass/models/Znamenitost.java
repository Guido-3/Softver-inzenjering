package com.citypass.models;

public class Znamenitost {
    private int id;
    private String ime;
    private String opis;
    private String slika;
    private String admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Znamenitost(int id, String ime, String opis, String slika, String admin) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.slika = slika;
        this.admin = admin;
    }

    public Znamenitost(){

    }
}
