package com.fancy.demo.Service;

import com.fancy.demo.Model.FancyDataOBJ;
import com.fancy.demo.Model.MBKUserFreeCharge;
import com.fancy.demo.dao.FancyBaseDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alex on 2017/4/15.
 */


@Service("fancyBaseDataService")

public class FancyBaseDataService {
    @Autowired
    private FancyBaseDataDao fancyBaseDataDao;

    public List<FancyDataOBJ> getSampleTop()
    {
        return fancyBaseDataDao.getTopSample();
    }

}
