package com.metehansargin.springlearn2.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledExample {

    @Scheduled(cron = "* * * 30 * *")
    public void write(){
        for (int i=0;i<10;i++){
            System.out.println(" "+i);
        }
    }
}
