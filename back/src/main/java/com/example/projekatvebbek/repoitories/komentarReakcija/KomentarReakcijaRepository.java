package com.example.projekatvebbek.repoitories.komentarReakcija;

import com.example.projekatvebbek.entities.KomentarReakcija;

import java.util.List;

public interface KomentarReakcijaRepository {
    public KomentarReakcija addKomentarReakcija(KomentarReakcija komentarReakcija);
    public List<KomentarReakcija> allKomentariReakcije();
    public KomentarReakcija findKomentarReakcija(Integer id);
    public void deleteKomentarReakcija(Integer id);
    public KomentarReakcija editKomentarReakcija(Integer id,KomentarReakcija komentarReakcija);
}
