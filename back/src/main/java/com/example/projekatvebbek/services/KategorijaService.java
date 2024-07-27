package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.Kategorija;
import com.example.projekatvebbek.repoitories.kategorije.KategorijaRepository;

import javax.inject.Inject;
import java.util.List;

public class KategorijaService {

    public KategorijaService() {
        System.out.println(this);
    }

    @Inject
    private KategorijaRepository kategorijaRepository;

    public Kategorija addKategorija(Kategorija kategorija){
        return this.kategorijaRepository.addKategorija(kategorija);
    }
    public List<Kategorija> allKategorije(){
        return this.kategorijaRepository.allKategorije();
    }
    public Kategorija findKategoriju(Integer id){
        return this.kategorijaRepository.findKategoriju(id);
    }
    public void deleteKategoriju(Integer id){
        this.kategorijaRepository.deleteKategoriju(id);
    }
    public Kategorija editKategorija(Integer id,Kategorija kategorija){
        return this.kategorijaRepository.editKategorija(id,kategorija);
    }

    public Kategorija findKategorijuByIme(String ime){
        return this.kategorijaRepository.findKategorijuByIme(ime);
    }

}
