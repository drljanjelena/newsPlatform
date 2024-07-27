package com.example.projekatvebbek.services;

import com.example.projekatvebbek.entities.VestReakcija;
import com.example.projekatvebbek.repoitories.vestReakcija.VestReakcijaReposiroty;

import javax.inject.Inject;
import java.util.List;

public class VestReakcijaService {

    public VestReakcijaService() {
        System.out.println(this);
    }

    @Inject
    private VestReakcijaReposiroty vestReakcijaReposiroty;

    public VestReakcija addVestReakciju(VestReakcija vestReakcija){
        return this.vestReakcijaReposiroty.addVestReakciju(vestReakcija);
    }
    public List<VestReakcija> allVestReakcije(){
        return this.vestReakcijaReposiroty.allVestReakcije();
    }
    public VestReakcija findVestReakcija(Integer id){
        return this.vestReakcijaReposiroty.findVestReakcija(id);
    }
    public void deleteVestReakcija(Integer id){
        this.vestReakcijaReposiroty.deleteVestReakcija(id);
    }
    public VestReakcija editVestReakcija(Integer id,VestReakcija vestReakcija){
        return this.vestReakcijaReposiroty.editVestReakcija(id,vestReakcija);
    }
}
