package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.Tag;
import com.example.projekatvebbek.repoitories.tag.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    public TagService() {
        System.out.println(this);
    }

    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag){
        return this.tagRepository.addTag(tag);
    }
    public List<Tag> allTag(){
        return this.tagRepository.allTag();
    }
    public Tag findTag(Integer id){
        return this.tagRepository.findTag(id);
    }
    public void deleteTag(Integer id){
        this.tagRepository.deleteTag(id);
    }
    public Tag editTag(Integer id,Tag tag){
        return this.tagRepository.editTag(id,tag);
    }
}
