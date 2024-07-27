package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.Komentar;
import com.example.projekatvebbek.repoitories.komentari.KomentarRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    public KomentarService() {
        System.out.println(this);
    }

    @Inject
    private KomentarRepository komentarRepository;

    public Komentar addKomentar(Komentar komentar){
        return this.komentarRepository.addKomentar(komentar);
    }
    public List<Komentar> allKomentari(){
        return this.komentarRepository.allKomentari();
    }
    public Komentar findKomentar(Integer id){
        return this.komentarRepository.findKomentar(id);
    }
    public void deleteKomentar(Integer id){
        this.komentarRepository.deleteKomentar(id);
    }
    public Komentar editKomentar(Integer id,Komentar komentar){
        return this.komentarRepository.editKomentar(id,komentar);
    }
    public List<Komentar> findByVest(Integer id){
        return this.komentarRepository.findByVest(id);
    }

}
