package com.fancy.demo.dao;

import org.apache.ibatis.annotations.*;
import com.fancy.demo.Model.MBKUserFreeCharge;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by alex on 2017/3/6.
 */

@Mapper
public interface MBKUserFreeChargeDao {

    //table:  userFreeCharge


    @Select("SELECT * from userFreeCharge LIMIT 20")
    List<MBKUserFreeCharge> getTopSample();



    @Select("SELECT * from  userFreeCharge  where user_id=#{usrid} lIMIT 1;")
    MBKUserFreeCharge getFreeContentByID(@Param("usrid") String usrid);


    @Insert("insert userFreeCharge(user_id,latest_freetime) values (#{freecharge.user_id},#{freecharge.latest_freetime})")
    void insertOBJ(@Param("freecharge") MBKUserFreeCharge mbkUserFreeCharge);

    @Update("update userFreeCharge set latest_freetime=#{freecharge.latest_freetime}  where user_id=#{freecharge.user_id}")
    void updateOBJ(@Param("freecharge") MBKUserFreeCharge mbkUserFreeCharge);


}
