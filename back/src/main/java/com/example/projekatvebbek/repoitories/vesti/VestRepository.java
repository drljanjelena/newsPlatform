package com.example.projekatvebbek.repoitories.vesti;

import com.example.projekatvebbek.entities.Vest;

import java.util.List;

public interface VestRepository {
    public Vest addVest(Vest vest);
    public List<Vest> allVesti();
    public Vest findVest(Integer id);
    public void deleteVest(Integer id);
    public Vest editVest(Integer id,Vest vest);
    public List<Vest> getVestiByCategory(Integer kategorijaId);
}
