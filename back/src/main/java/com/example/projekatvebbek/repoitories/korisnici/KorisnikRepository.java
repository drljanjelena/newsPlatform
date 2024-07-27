package com.example.projekatvebbek.repoitories.korisnici;

import com.example.projekatvebbek.entities.KomentarReakcija;
import com.example.projekatvebbek.entities.Korisnik;

import java.util.List;

public interface KorisnikRepository {
    public Korisnik addKorisnik(Korisnik korisnik);
    public List<Korisnik> allKorisnici();
    public Korisnik findKorisnik(Integer id);
    public void deleteKorisnik(Integer id);
    public Korisnik editKorisnik(Integer id,Korisnik korisnik);
    public Korisnik findKorisnikByEmail(String email);
}
