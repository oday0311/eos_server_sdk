package com.fancy.demo.Model;


import org.springframework.context.annotation.ComponentScan;

import lombok.Data;

/**
 * Created by alex on 2017/4/15.
 */

@ComponentScan
@Data
public class FancyDataOBJ {
    public String data_1;
    public String data_2;
    public String data_3;
}
