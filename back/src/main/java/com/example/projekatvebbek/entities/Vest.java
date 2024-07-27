package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Vest {

    private Integer id;

    @NotNull(message = "naslov field is required")
    @NotEmpty(message = "naslov field is required")
    private String naslov;

    @NotNull(message = "tekst field is required")
    @NotEmpty(message = "tekst field is required")
    private String tekst;
    private Date datumKreiranja;

    @NotNull(message = "autor field is required")
    private Integer autor; // id od korisnika koji je kreirao vest

    @NotNull(message = "kategorijaID field is required")
    private Integer kategorijaID;
    private int brojLajkova;
    private int brojDislajkova;
    private int brojPoseta;

    public Vest() {
    }

    public Vest(Integer id, String naslov, String tekst, Date datumKreiranja, Integer autor, Integer kategorijaID, int brojLajkova, int brojDislajkova, int brojPoseta) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datumKreiranja = datumKreiranja;
        this.autor = autor;
        this.kategorijaID = kategorijaID;
        this.brojLajkova = brojLajkova;
        this.brojDislajkova = brojDislajkova;
        this.brojPoseta = brojPoseta;
    }

    public Vest(String naslov, String tekst, Integer autor) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.autor = autor;
        this.datumKreiranja = new Date();
    }

    public Vest(String naslov, String tekst, Date datumKreiranja, Integer autor, int brojLajkova, int brojDislajkova, int brojPoseta) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.datumKreiranja = new Date();
        this.autor = autor;
        this.brojLajkova = brojLajkova;
        this.brojDislajkova = brojDislajkova;
        this.brojPoseta = brojPoseta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBrojPoseta() {
        return brojPoseta;
    }

    public void setBrojPoseta(int brojPoseta) {
        this.brojPoseta = brojPoseta;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
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

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }

    public int getBrojLajkova() {
        return brojLajkova;
    }

    public void setBrojLajkova(int brojLajkova) {
        this.brojLajkova = brojLajkova;
    }

    public int getBrojDislajkova() {
        return brojDislajkova;
    }

    public void setBrojDislajkova(int brojDislajkova) {
        this.brojDislajkova = brojDislajkova;
    }

    public Integer getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Integer kategorijaID) {
        this.kategorijaID = kategorijaID;
    }
}
