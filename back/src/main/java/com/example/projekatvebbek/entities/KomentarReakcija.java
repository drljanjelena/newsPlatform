package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class KomentarReakcija {

    private Integer id;
    @NotNull(message = "komentarId field is required")
    @NotEmpty(message = "komentarId field is required")
    private Integer komentarId;

    @NotNull(message = "korisnikId field is required")
    @NotEmpty(message = "korisnikId field is required")
    private Integer korisnikId;

    @NotNull(message = "tipReakcije field is required")
    @NotEmpty(message = "tipReakcije field is required")
    private String tipReakcije; // lajk dislajk

    public KomentarReakcija() {
    }

    public KomentarReakcija(Integer id, Integer komentarId, Integer korisnikId, String tipReakcije) {
        this.id = id;
        this.komentarId = komentarId;
        this.korisnikId = korisnikId;
        this.tipReakcije = tipReakcije;
    }

    public KomentarReakcija(Integer komentarId, Integer korisnikId, String tipReakcije) {
        this.komentarId = komentarId;
        this.korisnikId = korisnikId;
        this.tipReakcije = tipReakcije;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKomentarId() {
        return komentarId;
    }

    public void setKomentarId(Integer komentarId) {
        this.komentarId = komentarId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getTipReakcije() {
        return tipReakcije;
    }

    public void setTipReakcije(String tipReakcije) {
        this.tipReakcije = tipReakcije;
    }
}
