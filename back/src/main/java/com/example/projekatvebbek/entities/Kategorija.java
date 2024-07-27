package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Kategorija {

    private Integer id;

    @NotNull(message = "ime field is required")
    @NotEmpty(message = "ime field is required")
    private String ime;

    @NotNull(message = "opis field is required")
    @NotEmpty(message = "opis field is required")
    private String opis;

    public Kategorija() {
    }

    public Kategorija(Integer id, String ime, String opis) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
    }

    public Kategorija(String ime, String opis) {
        this.ime = ime;
        this.opis = opis;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}

