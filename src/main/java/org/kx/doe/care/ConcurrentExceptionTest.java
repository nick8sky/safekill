package org.kx.doe.care;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create by sunkx on 2018/3/12
 */
public class ConcurrentExceptionTest {
    public static void main(String... s) throws InterruptedException {
        //test1();
        test10();
    }


    public static void test1() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }

        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                arrayList.remove(integer);
            }
        }
    }

    public static void test2() {
        List<User> list1 = new ArrayList<User>();
        for (int x = 0; x < 15; x++) {
            User user2 = new User(x + "x", 1l);
            if (list1.isEmpty()) {
                list1.add(user2);
            } else {
                for (User ecity : list1) {  //ex .java.util.ConcurrentModificationException
                    if (user2.getName().equals(ecity.getName())) {
                        ecity.setCc(ecity.getCc() + 1);
                    } else {
                        list1.add(user2);
                    }
                }
            }
        }
        for (User u : list1) {
            System.out.println(u);
        }
    }

    public static void test7() {
        List<User> list1 = new ArrayList<User>();
        for (int x = 0; x < 15; x++) {
            User user2 = new User(x + "x", 1l);
            if (list1.isEmpty()) {
                list1.add(user2);
            } else {
                for (int xd=0;xd< list1.size() ; xd ++) {  //ex .java.util.ConcurrentModificationException
                    User ecity =  list1.get(xd);
                    if (user2.getName().equals(ecity.getName())) {
                        ecity.setCc(ecity.getCc() + 1);
                    } else {
                        list1.add(user2);
                    }
                }
            }
        }
        for (User u : list1) {
            System.out.println(u);
        }
    }

    public static void test3() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        for(int i =19;i>=0;i--){
            if(i %2 == 0){
                arrayList.remove(i);
            }
            System.out.println(arrayList.get(i));
        }

    }
    public static void test4() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        for(int i = 0; i < 20; i++){
            if(i %2 == 0){
                arrayList.remove(i);
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public static void test5() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        // 复现方法二
        Iterator<Integer>    iterator = arrayList.iterator();
                 for (Integer value : arrayList) {
                         Integer integer = iterator.next();
                         if (integer.intValue() == 5) {
                                 arrayList.remove(integer);
                             }
                     }
    }


    public static void test6() {
        List<Integer> list1 = new ArrayList<Integer>();
        for (int x = 0; x < 15; x++) {

            if (list1.isEmpty()) {
                list1.add(x);
            } else {
                for (Integer ecity : list1) {  //ex .java.util.ConcurrentModificationException

                        list1.add(x);

                }
            }
        }

    }

    public static void test8() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }

        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                iterator.remove();
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }


    public static void test9() {
        final List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ListIterator<Integer> iterator = list.listIterator();
                while (iterator.hasNext()) {
                    System.out.println("thread1 " + iterator.next().intValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer integer : list) {
                    System.out.println("thread2 " + integer.intValue());
                    if (integer.intValue() == 5) {
                        list.remove(integer);
                    }
                }
                for (Integer integer : list) {
                    System.out.println("thread2 again " + integer.intValue());
                }

            }
        });
        thread1.start();
        thread2.start();
    }


    public static void test10() {
        final List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int x=list.size()-1 ;x>=0; x--) {
                    x ++;
                    if(x ==1) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("thread1  " + list.get(x).intValue());
                }

               /* ListIterator<Integer> iterator = list.listIterator();
                while (iterator.hasNext()) {
                    System.out.println("thread1 " + iterator.next().intValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer integer : list) {
                    System.out.println("thread2 " + integer.intValue());
                    if (integer.intValue() == 5) {
                        list.remove(integer);
                    }
                }
                for (Integer integer : list) {
                    System.out.println("thread2 again " + integer.intValue());
                }

            }
        });
        thread1.start();
        thread2.start();
    }
}

