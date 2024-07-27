package com.example.projekatvebbek.repoitories.kategorije;

import com.example.projekatvebbek.entities.Kategorija;

import java.util.List;

public interface KategorijaRepository {
    public Kategorija addKategorija(Kategorija kategorija);
    public List<Kategorija> allKategorije();
    public Kategorija findKategoriju(Integer id);
    public void deleteKategoriju(Integer id);
    public Kategorija editKategorija(Integer id,Kategorija kategorija);
    public Kategorija findKategorijuByIme(String ime);

}
