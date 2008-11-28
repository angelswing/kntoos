package com.conant.ums.util.tree;

import java.util.*;

public class TreeHelper {
    public static final String ROOTID = "\\";
    public TreeHelper() {
    }

    //创建树
    public static TreeNode makeTree(List ltNodes, Map hmNodes) {
        TreeNode trRoot = new TreeNode();
        trRoot.setName("root");
        TreeInterface trObj = null;
        String sPid = null;
        for (int i = 0; i < ltNodes.size(); i++) {
            trObj = (TreeInterface) ltNodes.get(i);
            sPid = trObj.getPid();
            //if parent is root , add to root's children
            if (sPid.equals(ROOTID)) {
                trRoot.addChild(trObj);
            }
            else {
                //if parent exist in map, add to its children
                if (hmNodes.containsKey(sPid)) {
                    TreeNode trNode = (TreeNode) hmNodes.get(sPid);
                    trNode.addChild(trObj);
                }
            }
        }
        return trRoot;
    }

    //创建树
    public static TreeNode makeTree(List ltNodes, Map hmNodes, String sROOTID) {
        TreeNode trRoot = new TreeNode();
        trRoot.setName("root");
        TreeInterface trObj = null;
        String sPid = null;
        for (int i = 0; i < ltNodes.size(); i++) {
            trObj = (TreeInterface) ltNodes.get(i);
            sPid = trObj.getPid();
            //if parent is root , add to root's children
            if (sPid.equals(sROOTID)) {
                trRoot.addChild(trObj);
            }
            else {
                //if parent exist in map, add to its children
                if (hmNodes.containsKey(sPid)) {
                    TreeNode trNode = (TreeNode) hmNodes.get(sPid);
                    trNode.addChild(trObj);
                }
            }
        }
        return trRoot;
    }

    //清除根结点下，无子结点（或叶子结点）的树结点
    public static void removeEmptyNode(TreeNode trNode) {
        if (!trNode.getChildren().isEmpty()) {
            //遍历当前结点的所有子结点
            for (int i = 0; i < trNode.getChildren().size(); ) {
                TreeInterface child = (TreeInterface) trNode.getChildren().get(
                    i);
                //如果子结点非叶子结点，检查其下级子结点集是否为空
                if (child.getType() == TreeInterface.NODE) {
                    TreeNode childNode = (TreeNode) child;
                    //如果为空则清除该子结点，记数器不变
                    if (childNode.getChildren().isEmpty()) {
                        trNode.getChildren().remove(i);
                        continue;
                    }
                    else {
                        //如果不为空，则递归检查其下级子结点
                        removeEmptyNode(childNode);
                    }
                    //检查下级结点是否都被清除，如都被清除，则本结点也需要被清除
                    if (childNode.getChildren().isEmpty()) {
                        trNode.getChildren().remove(i);
                        continue;
                    }
                }
                i++;
            }

        }
    }

}
