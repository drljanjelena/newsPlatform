package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class Tag {
    private Integer id;

    @NotNull(message = "naziv field is required")
    @NotEmpty(message = "naziv field is required")
    private String naziv;

    public Tag() {
    }

    public Tag(String naziv) {
        this.naziv = naziv;
    }

    public Tag(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}

