package com.fancy.demo.Model;

import org.springframework.context.annotation.ComponentScan;

import lombok.Data;

/**
 * Created by alex on 2017/3/6.
 */

@ComponentScan
@Data
public class MBKUserFreeCharge extends BaseObj {

    public long id;
    public String user_id;
    public long latest_freetime;

}
