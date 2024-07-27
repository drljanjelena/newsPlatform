package com.example.projekatvebbek.repoitories.vestReakcija;

import com.example.projekatvebbek.entities.VestReakcija;
import com.example.projekatvebbek.entities.VestTag;

import java.util.List;

public interface VestReakcijaReposiroty {
    public VestReakcija addVestReakciju(VestReakcija vestReakcija);
    public List<VestReakcija> allVestReakcije();
    public VestReakcija findVestReakcija(Integer id);
    public void deleteVestReakcija(Integer id);
    public VestReakcija editVestReakcija(Integer id,VestReakcija vestReakcija);
}
