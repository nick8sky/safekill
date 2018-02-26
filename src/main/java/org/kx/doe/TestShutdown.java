package org.kx.doe;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by sunkx on 2018/2/26
 */
public class TestShutdown {
    //简单模拟干活的
    static Timer timer = new Timer("job-timer");

    //计数干活次数
    static AtomicInteger count = new AtomicInteger(0);

    /**
     * hook线程
     */
    static class CleanWorkThread extends Thread{
        @Override
        public void run() {
            System.out.println("clean some work.");
            System.out.println(new Date());
            timer.cancel();
            try {
                Thread.sleep(8 * 1000);//sleep 8s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date());
            System.out.println("clean completed .");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //将hook线程添加到运行时环境中去
        Runtime.getRuntime().addShutdownHook(new CleanWorkThread());
        System.out.println("main class start ..... ");
        //简单模拟
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count.getAndIncrement();
                System.out.println("doing job " + count);
                // kill 3769
                /*if (count.get() == 10) {  //干了10次退出
                    System.exit(0);
                }*/
            }
        }, 0, 2 * 1000);

    }
}
