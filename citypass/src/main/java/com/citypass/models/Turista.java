package com.citypass.models;

public class Turista {
    private int id;
    private String ime;
    private String prezime;
    private String drzava_ime;

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

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDrzava_ime() {
        return drzava_ime;
    }

    public void setDrzava_ime(String drzava_ime) {
        this.drzava_ime = drzava_ime;
    }

    public Turista(int id, String ime, String prezime, String drzava_ime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.drzava_ime = drzava_ime;
    }

    public Turista() {

    }
}