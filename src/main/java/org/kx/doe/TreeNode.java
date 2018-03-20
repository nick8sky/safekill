package org.kx.doe;

import java.util.List;

/**
 * create by sunkx on 2018/3/10
 */
public class TreeNode {
    private List<TreeNode> m;
    private int level ;
    private TreeNode praentNode;

    public List<TreeNode> getM() {
        return m;
    }

    public void setM(List<TreeNode> m) {
        this.m = m;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TreeNode getPraentNode() {
        return praentNode;
    }

    public void setPraentNode(TreeNode praentNode) {
        this.praentNode = praentNode;
    }
}
