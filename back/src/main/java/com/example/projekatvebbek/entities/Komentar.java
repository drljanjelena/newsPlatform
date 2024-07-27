package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Komentar {

    private Integer id;
    @NotNull(message = "imeAutora field is required")
    @NotEmpty(message = "imeAutora field is required")
    private String imeAutora;

    @NotNull(message = "tekst field is required")
    @NotEmpty(message = "tekst field is required")
    private String tekst;

    private Date datumKreiranja;

    @NotNull(message = "korisnikId field is required")
    @NotEmpty(message = "korisnikId field is required")
    private Integer korisnikId;
    @NotNull(message = "vestId field is required")
    @NotEmpty(message = "vestId field is required")
    private Integer vestId;

    public Komentar() {
    }

    public Komentar(Integer id, String imeAutora, String tekst, Date datumKreiranja, Integer korisnikId, Integer vestId) {
        this.id = id;
        this.imeAutora = imeAutora;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
        this.korisnikId = korisnikId;
        this.vestId = vestId;
    }

    public Komentar(String imeAutora, String tekst) {
        this.imeAutora = imeAutora;
        this.tekst = tekst;
        this.datumKreiranja = new Date();
    }

    public Komentar(String imeAutora, String tekst, Date datumKreiranja, Integer korisnikId, Integer vestId) {
        this.imeAutora = imeAutora;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
        this.korisnikId = korisnikId;
        this.vestId = vestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Integer getVestId() {
        return vestId;
    }

    public void setVestId(Integer vestId) {
        this.vestId = vestId;
    }
}

