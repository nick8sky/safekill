package org.kx.doe;

/**
 * create by sunkx on 2018/2/26
 */
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("restriction")
public class SignalTest {
    public static void main(String[] args) throws InterruptedException {
        // 信号处理实例
        MySignalHandler mySignalHandler = new MySignalHandler();

        // 注册对指定信号的处理
        Signal.handle(new Signal("TERM") ,mySignalHandler);    // kill or kill -15
        Signal.handle(new Signal("INT"), mySignalHandler);     // kill -2
        System.out.println("[Thread:"+Thread.currentThread().getName() + "] is sleep" );
        while(true) Thread.sleep(1000);
    }
}

@SuppressWarnings("restriction")
class MySignalHandler implements SignalHandler {
    public void handle(Signal signal) {
        // 信号量名称
        String name = signal.getName();
        // 信号量数值
        int number = signal.getNumber();

        // 当前进程名
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[Thread:"+currentThreadName + "] receved signal: " + name + " == kill -" + number);
        if(name.equals("TERM")){
           // System.exit(0);
            System.out.println(name);
        }
    }



    public  void Test1(){
        String[] aa = {"a","b","c"};
        ArrayList list = (ArrayList) Arrays.asList(aa);
        ArrayList list1 = new ArrayList(3);
        for(int x =list.size() -1;  x>=0;x--){
            list1.add(list.get(x));
            System.out.println(list.get(x));
        }



    }

}




