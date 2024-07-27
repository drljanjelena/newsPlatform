package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VestReakcija {

    private Integer id;
    @NotNull(message = "vestId field is required")
    @NotEmpty(message = "vestId field is required")
    private Integer vestId;

    @NotNull(message = "korisnikId field is required")
    @NotEmpty(message = "korisnikId field is required")
    private Integer korisnikId;

    @NotNull(message = "tipReakcije field is required")
    @NotEmpty(message = "tipReakcije field is required")
    private String tipReakcije; // lajk dislajk

    public VestReakcija() {
    }

    public VestReakcija(Integer id, Integer vestId, Integer korisnikId, String tipReakcije) {
        this.id = id;
        this.vestId = vestId;
        this.korisnikId = korisnikId;
        this.tipReakcije = tipReakcije;
    }

    public VestReakcija(Integer vestId, Integer korisnikId, String tipReakcije) {
        this.vestId = vestId;
        this.korisnikId = korisnikId;
        this.tipReakcije = tipReakcije;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVestId() {
        return vestId;
    }

    public void setVestId(Integer vestId) {
        this.vestId = vestId;
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
