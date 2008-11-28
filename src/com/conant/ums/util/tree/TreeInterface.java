
/**
 *  TreeObject.java
 */

package com.conant.ums.util.tree;

public interface TreeInterface{
    static public final int LEAF = 0;
    static public final int NODE = 1;

    public int getType();
    public String getId();
    public String getName();
    public String getPid();

}
