package com.conant.ums.util.tree;

import java.util.*;

public class TreeHelper {
    public static final String ROOTID = "\\";
    public TreeHelper() {
    }

    //������
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

    //������
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

    //���������£����ӽ�㣨��Ҷ�ӽ�㣩�������
    public static void removeEmptyNode(TreeNode trNode) {
        if (!trNode.getChildren().isEmpty()) {
            //������ǰ���������ӽ��
            for (int i = 0; i < trNode.getChildren().size(); ) {
                TreeInterface child = (TreeInterface) trNode.getChildren().get(
                    i);
                //����ӽ���Ҷ�ӽ�㣬������¼��ӽ�㼯�Ƿ�Ϊ��
                if (child.getType() == TreeInterface.NODE) {
                    TreeNode childNode = (TreeNode) child;
                    //���Ϊ����������ӽ�㣬����������
                    if (childNode.getChildren().isEmpty()) {
                        trNode.getChildren().remove(i);
                        continue;
                    }
                    else {
                        //�����Ϊ�գ���ݹ������¼��ӽ��
                        removeEmptyNode(childNode);
                    }
                    //����¼�����Ƿ񶼱�������綼��������򱾽��Ҳ��Ҫ�����
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
