package com.citypass.models;

public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String br_telefona;
    private String drzava_ime;
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

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBr_telefona() {
        return br_telefona;
    }

    public void setBr_telefona(String br_telefona) {
        this.br_telefona = br_telefona;
    }

    public String getDrzava_ime() {
        return drzava_ime;
    }

    public void setDrzava_ime(String drzava_ime) {
        this.drzava_ime = drzava_ime;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Korisnik(int id, String ime, String prezime, String email, String br_telefona, String drzava_ime, String admin) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.br_telefona = br_telefona;
        this.drzava_ime = drzava_ime;
        this.admin = admin;
    }

    public Korisnik() {

    }
}
