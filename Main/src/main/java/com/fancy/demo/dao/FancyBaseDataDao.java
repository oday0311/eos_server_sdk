package com.fancy.demo.dao;

import java.util.List;

import com.fancy.demo.Model.FancyDataOBJ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * Created by alex on 2017/4/15.
 */

@Mapper
public interface FancyBaseDataDao {

    @Select("SELECT * from mbkfancy LIMIT 1")
    List<FancyDataOBJ> getTopSample();


}
