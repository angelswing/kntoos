/**
 *  TreeNode.java
 */

package com.conant.ums.util.tree;

import java.io.Serializable;
import java.util.*;

public class TreeNode
    implements TreeInterface, Serializable {

    private int type;
    private List children;
    private String id;
    private String name;
    private boolean selected;
    //parent id
    private String pid;
    private String link;

    public TreeNode(String id, String pid, String name) {
        this.type = TreeInterface.NODE;
        this.id = id;
        this.pid = pid;
        this.name = name;
        children = new ArrayList();
    }

    public TreeNode(String id, String pid, String name, String link) {
        this.type = TreeInterface.NODE;
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.link = link;
        children = new ArrayList();
    }

    public TreeNode(String id, String pid, String name, boolean selected) {
        this.type = TreeInterface.NODE;
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.selected = selected;
        children = new ArrayList();
    }

    public TreeNode() {
        this.type = TreeInterface.NODE;
        children = new ArrayList();
    }

    public void addChild(TreeInterface child) {
        children.add(child);
    }

    public List getChildren() {
        return children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getLink() {
        return link;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
