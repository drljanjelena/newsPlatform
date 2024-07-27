package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.KomentarReakcija;
import com.example.projekatvebbek.repoitories.komentarReakcija.KomentarReakcijaRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarReakcijaService {

    public KomentarReakcijaService() {
        System.out.println(this);
    }

    @Inject
    private KomentarReakcijaRepository komentarReakcijaRepository;

    public KomentarReakcija addKomentarReakcija(KomentarReakcija komentarReakcija){
        return this.komentarReakcijaRepository.addKomentarReakcija(komentarReakcija);
    }
    public List<KomentarReakcija> allKomentariReakcije(){
        return this.komentarReakcijaRepository.allKomentariReakcije();
    }
    public KomentarReakcija findKomentarReakcija(Integer id){
        return this.komentarReakcijaRepository.findKomentarReakcija(id);
    }
    public void deleteKomentarReakcija(Integer id){
        this.komentarReakcijaRepository.deleteKomentarReakcija(id);
    }
    public KomentarReakcija editKomentarReakcija(Integer id,KomentarReakcija komentarReakcija){
        return this.komentarReakcijaRepository.editKomentarReakcija(id,komentarReakcija);
    }

}
