package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;
    @NotNull(message = "ime field is required")
    @NotEmpty(message = "ime field is required")
    private String ime;

    @NotNull(message = "prezime field is required")
    @NotEmpty(message = "prezime field is required")
    private String prezime;

    @NotNull(message = "email field is required")
    @NotEmpty(message = "email field is required")
    private String email;

    @NotNull(message = "tipKorisnika field is required")
    @NotEmpty(message = "tipKorisnika field is required")
    private String tipKorisnika;

    @NotNull(message = "status field is required")
    @NotEmpty(message = "status field is required")
    private String status;

    @NotNull(message = "hashLozinke field is required")
    @NotEmpty(message = "hashLozinke field is required")
    private String hashLozinke;

    public Korisnik() {
    }

    public Korisnik(Integer id, String ime, String prezime, String email, String tipKorisnika, String status, String hashLozinke) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tipKorisnika = tipKorisnika;
        this.status = status;
        this.hashLozinke = hashLozinke;
    }

    public Korisnik(String ime, String prezime, String email, String tipKorisnika, String status, String hashLozinke) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tipKorisnika = tipKorisnika;
        this.status = status;
        this.hashLozinke = hashLozinke;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHashLozinke() {
        return hashLozinke;
    }

    public void setHashLozinke(String hashLozinke) {
        this.hashLozinke = hashLozinke;
    }
}
