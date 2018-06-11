package com.fancy.demo.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alex on 2017/5/3.
 */

@Service("cleanWXRedpacketCacheSchedule")
public class CleanWXRedpacketCacheSchedule {

    private final static Logger logger = LoggerFactory.getLogger(CleanWXRedpacketCacheSchedule.class);

    private final static long daytimelong = 86400000L;



    /**
     * 每天凌晨一点
     */
    private static final String cron = "0 00 01 * * ?";

    //测试的时候， 每分钟执行一次
    //private static final String cron = "0 0/1 * * * ?";


    static {
        logger.info("*****************************************");
        logger.info("*** work() Spring计划任务   已被初始化");
        logger.info("*****************************************");
    }


    /**
     * 每天凌晨一点
     */
    @Scheduled(cron = cron)
    public void schedule()
    {
        //StopWatch timer  = new StopWatch();
        //timer.start();
        //logger.info("*****************************************本次任务执行开始时间为:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timer.getStartTime())));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cleanRedis();


        //timer.stop();
        //logger.info("*****************************************本次任务执行结束时间为:" + new Date(timer.getTime()));
        //logger.info("*****************************************本次任务执行共计耗时:" + (timer.toString() + "(milliseconds)"));

    }


    public void cleanRedis()
    {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long currentTc = timestamp.getTime();
        //20天前的时间
        long oldTC = currentTc - daytimelong * 20;



    }
}
