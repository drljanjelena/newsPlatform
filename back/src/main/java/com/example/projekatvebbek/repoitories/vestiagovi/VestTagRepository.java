package com.example.projekatvebbek.repoitories.vestiagovi;

import com.example.projekatvebbek.entities.Vest;
import com.example.projekatvebbek.entities.VestTag;

import java.util.List;

public interface VestTagRepository {
    public VestTag addVestTag(VestTag vestTag);
    public List<VestTag> allVestTag();
    public VestTag findVestTag(Integer id);
    public void deleteVestTag(Integer id);
    public VestTag editVestTag(Integer id,VestTag vestTag);
}
