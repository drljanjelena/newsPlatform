package com.example.projekatvebbek.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VestTag {
    private Integer id;

    @NotNull(message = "vestId field is required")
    private Integer vestId;

    @NotNull(message = "tagId field is required")
    private Integer tagId;

    public VestTag() {
    }

    public VestTag(Integer id, Integer vestId, Integer tagId) {
        this.id = id;
        this.vestId = vestId;
        this.tagId = tagId;
    }

    public VestTag(Integer vestId, Integer tagId) {
        this.vestId = vestId;
        this.tagId = tagId;
    }

    public Integer getVestId() {
        return vestId;
    }

    public void setVestId(Integer vestId) {
        this.vestId = vestId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

