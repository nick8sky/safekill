package org.kx.doe;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by sunkx on 2018/2/26
 */
public class TreeTest {

    public static void main(String[] args) throws InterruptedException {
        //将hook线程添加到运行时环境中去
        int[] li = {2,3,4,3,2};
        List<Integer> list = new ArrayList<>();
        list.add(2);list.add(3);list.add(4);list.add(3);list.add(2);

        TreeNode root = new TreeNode();
        root.setLevel(1);
        root.setPraentNode(null);
        List<TreeNode> m = new ArrayList<>();
        root.setM(m);

        int lastLevel = root.getLevel();

        for(int i=0;i<5;i++){
            TreeNode treeNode2 = new TreeNode();
            treeNode2.setLevel(list.get(i));
            treeNode2.setPraentNode(null);


        }


    }
}
