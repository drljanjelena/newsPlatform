package com.example.projekatvebbek.repoitories.komentari;

import com.example.projekatvebbek.entities.Komentar;

import java.util.List;

public interface KomentarRepository {
    public Komentar addKomentar(Komentar komentar);
    public List<Komentar> allKomentari();
    public Komentar findKomentar(Integer id);
    public void deleteKomentar(Integer id);
    public Komentar editKomentar(Integer id,Komentar komentar);
    public List<Komentar> findByVest(Integer id);

}
