package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.VestTag;
import com.example.projekatvebbek.repoitories.vestiagovi.VestTagRepository;

import javax.inject.Inject;
import java.util.List;

public class VestTagService {

    public VestTagService() {
        System.out.println(this);
    }

    @Inject
    private VestTagRepository vestTagRepository;

    public VestTag addVestTag(VestTag vestTag){
        return this.vestTagRepository.addVestTag(vestTag);
    }
    public List<VestTag> allVestTag(){
        return this.vestTagRepository.allVestTag();
    }
    public VestTag findVestTag(Integer id){
        return this.vestTagRepository.findVestTag(id);
    }
    public void deleteVestTag(Integer id){
        this.vestTagRepository.deleteVestTag(id);
    }
    public VestTag editVestTag(Integer id,VestTag vestTag){
        return this.vestTagRepository.editVestTag(id,vestTag);
    }
}
