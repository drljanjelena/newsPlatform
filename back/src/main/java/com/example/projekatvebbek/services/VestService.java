package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.Vest;
import com.example.projekatvebbek.repoitories.vesti.VestRepository;

import javax.inject.Inject;
import java.util.List;

public class VestService {
    public VestService() {
        System.out.println(this);
    }

    @Inject
    private VestRepository vestRepository;

    public Vest addVest(Vest vest){
        return this.vestRepository.addVest(vest);
    }
    public List<Vest> allVesti(){
        return this.vestRepository.allVesti();
    }
    public Vest findVest(Integer id){
        return this.vestRepository.findVest(id);
    }
    public void deleteVest(Integer id){
        this.vestRepository.deleteVest(id);
    }
    public Vest editVest(Integer id,Vest vest){
        return this.vestRepository.editVest(id,vest);
    }
    public List<Vest> getVestiByCategory(Integer kategorijaId){return this.vestRepository.getVestiByCategory(kategorijaId);}
}
