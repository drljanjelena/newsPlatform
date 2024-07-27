package com.example.projekatvebbek.repoitories.tag;

import com.example.projekatvebbek.entities.Korisnik;
import com.example.projekatvebbek.entities.Tag;

import java.util.List;

public interface TagRepository {
    public Tag addTag(Tag tag);
    public List<Tag> allTag();
    public Tag findTag(Integer id);
    public void deleteTag(Integer id);
    public Tag editTag(Integer id,Tag tag);
}
