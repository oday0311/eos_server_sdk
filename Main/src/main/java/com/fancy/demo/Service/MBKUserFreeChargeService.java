package com.fancy.demo.Service;


import com.fancy.demo.Model.MBKUserFreeCharge;
import com.fancy.demo.dao.MBKUserFreeChargeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alex on 2017/3/6.
 */

@Service("mbkUserFreeChargeService")
public class MBKUserFreeChargeService {

    @Autowired
    private MBKUserFreeChargeDao mbkUserFreeChargeDao;


    public List<MBKUserFreeCharge> getSampleTop()
    {
        return mbkUserFreeChargeDao.getTopSample();
    }

    public MBKUserFreeCharge getFreeContentByID(String userid){
        return mbkUserFreeChargeDao.getFreeContentByID( userid);
    }

    public void updateFreeChargeByID(String userid,long time)
    {
        MBKUserFreeCharge result = mbkUserFreeChargeDao.getFreeContentByID(userid);
        if (result == null)
        {
            MBKUserFreeCharge freecharge = new MBKUserFreeCharge();
            freecharge.user_id = userid;
            freecharge.latest_freetime = time;

            mbkUserFreeChargeDao.insertOBJ(freecharge);
        }
        else
        {
            MBKUserFreeCharge freecharge = result;

            freecharge.latest_freetime = freecharge.latest_freetime+time;
            mbkUserFreeChargeDao.updateOBJ(freecharge);

        }

    }

}
