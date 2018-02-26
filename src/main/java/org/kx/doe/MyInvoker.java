package org.kx.doe;

import java.util.Date;

/**
 * create by sunkx on 2018/2/26
 */
public class MyInvoker {
    public static  void main(String ...s) throws InterruptedException {
        while (true){
            Thread.sleep(5000L);
            System.out.println(new Date());
        }

    }
}
