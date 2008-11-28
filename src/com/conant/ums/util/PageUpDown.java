package com.conant.ums.util;

import java.util.*;



public class PageUpDown {
    public PageUpDown() {
    }

    //新增记录所在行数
    private int iRowNo = 0;
    //当前页
    private int iPageNo = 0;
    //总页数
    private int iPageNumber = 0;
    //每页显示条数
    private int iSigPageNumber = 10;

    public PageUpDown(int iPageNo) {
        if (iPageNo > 0) {
            this.iPageNo = iPageNo;
        }
        else {
            this.iPageNo = 1;
        }
    }

    public PageUpDown(int iPageNo, int iSigPageNumber) {
        int pageNo = 0;
        if (iPageNo > 0) {
            this.iPageNo = iPageNo;
        }
        else {
            this.iPageNo = 1;
        }
        if (iSigPageNumber > 0) {
            this.iSigPageNumber = iSigPageNumber;
        }
        else {
            this.iSigPageNumber = 30;
        }
    }

    //从数组集合中取出子集, 以列表集合返回
    public List getPageSet(Object[] oInputData) {

        List listV = new ArrayList();
        Object[] list = null;

        int allPage = 0;
        try {
            if (oInputData != null && oInputData.length != 0) {
                if (oInputData.length % iSigPageNumber == 0) {
                    allPage = oInputData.length / iSigPageNumber;
                }
                else {
                    allPage = oInputData.length / iSigPageNumber + 1;
                }
                int iRecNum = oInputData.length;
                for (int i = (this.iPageNo - 1) * this.iSigPageNumber;
                     i < this.iPageNo * this.iSigPageNumber && i < iRecNum; i++) {
                    listV.add(oInputData[i]);
                }

            }
            this.iPageNumber = allPage;
            if (this.iPageNumber == 0) {
                this.iPageNo = 0;
            }
        }
        catch (Exception e) {
            Log.error("the exception is " + e);
        }
        return listV;
    }

    //从向量集合中取出子集, 以列表集合返回
    public List getPageSet(List oInputData) {

        List listV = new ArrayList();
        Object[] list = null;

        int allPage = 0;
        try {
            if (oInputData != null && oInputData.size() != 0) {
                if (oInputData.size() % iSigPageNumber == 0) {
                    allPage = oInputData.size() / iSigPageNumber;
                }
                else {
                    allPage = oInputData.size() / iSigPageNumber + 1;
                }
                int iRecNum = oInputData.size();
                if (allPage < iPageNo) {
                    iPageNo = iPageNo - 1;
                }
                for (int i = (this.iPageNo - 1) * this.iSigPageNumber;
                     i < this.iPageNo * this.iSigPageNumber && i < iRecNum; i++) {
                    listV.add(oInputData.get(i));
                }

            }
            this.iPageNumber = allPage;
            if (this.iPageNumber == 0) {
                this.iPageNo = 0;
            }

        }
        catch (Exception e) {
            Log.error("the exception is " + e);
        }
        return listV;
    }

    //从向量集合中取出包含指定记录的子集合，并以列表集合返回
    public List getPageSet_NewRecord(List oInputData, List idList, String key) {

        List listV = new ArrayList();
        Object[] list = null;

        int allPage = 0;
        try {
            if (oInputData != null && oInputData.size() != 0) {
                if (oInputData.size() % iSigPageNumber == 0) {
                    allPage = oInputData.size() / iSigPageNumber;
                }
                else {
                    allPage = oInputData.size() / iSigPageNumber + 1;
                }
                int iRecNum = oInputData.size();
                for (int i = 0; i < iRecNum; i++) {
                    if ( ( (String) idList.get(i)).trim().equals(key.trim())) {
                        if ( (i + 1) % iSigPageNumber == 0) {
                            this.iPageNo = (i + 1) / iSigPageNumber;
                            if (iPageNo == 0) {
                                iPageNo = 1;
                            }
                        }
                        else {
                            this.iPageNo = i / iSigPageNumber + 1;
                        }
                        if ( (i + 1) % iSigPageNumber != 0) {
                            iRowNo = (i + 1) % iSigPageNumber;
                        }
                        else {
                            iRowNo = iSigPageNumber;
                        }
                        break;
                    }
                }
                for (int i = (this.iPageNo - 1) * this.iSigPageNumber;
                     i < this.iPageNo * this.iSigPageNumber && i < iRecNum; i++) {
                    listV.add(oInputData.get(i));
                }
            }
            this.iPageNumber = allPage;
            if (this.iPageNumber == 0) {
                this.iPageNo = 0;
            }

        }
        catch (Exception e) {
            Log.error("the exception is " + e);
        }
        return listV;
    }

    public List getPageSet_Delete(List oInputData, int iRowno) {

        List listV = new ArrayList();
        Object[] list = null;

        int allPage = 0;
        try {
            if (oInputData != null && oInputData.size() != 0) {
                if (oInputData.size() % iSigPageNumber == 0) {
                    allPage = oInputData.size() / iSigPageNumber;
                }
                else {
                    allPage = oInputData.size() / iSigPageNumber + 1;
                }
                int iRecNum = oInputData.size();
                //如果删除的第一行则翻到上页并将光标定位在最后一行
                if (iRowno == 1 && iPageNo != 1) {
                    iPageNo = iPageNo - 1;
                    this.iRowNo = iSigPageNumber;
                }
                else if (iRowno == 1 && iPageNo == 1) {
                    iRowNo = 1;
                    //否则光标定位在上一行
                }
                else {
                    iRowNo = iRowno - 1;
                }
                for (int i = (this.iPageNo - 1) * this.iSigPageNumber;
                     i < this.iPageNo * this.iSigPageNumber && i < iRecNum; i++) {
                    listV.add(oInputData.get(i));
                }

            }
            this.iPageNumber = allPage;
            if (this.iPageNumber == 0) {
                this.iPageNo = 0;
            }

        }
        catch (Exception e) {
            Log.error("the exception is " + e);
        }
        return listV;
    }

    // added by raokun 2006.19.02 begin. for cursor postion.
    // 本方法主要用来确定新增记录的时候,光标的定位.
    // 输入参数:
    //             oInputData: 为所有要显示的记录
    //             curLineNo:  新增的记录在所有记录中的绝对位置(系统需要将光标停留在此条记录上.
    //                         此处需要计算其在每页中的相对位置及处在哪一页)
    // 返回参数:
    //             List:       从所有记录中剔除掉非本页的记录,只返回单页的数据.
    public List getPageSet(List oInputData, int absolutePosition) {

        List listV = new ArrayList();
        //Obct[] list = null;
        Object[] list = null;

        int allPage = 0;
        try {
            if (oInputData != null && oInputData.size() != 0) {
                if (oInputData.size() % iSigPageNumber == 0) {
                    allPage = oInputData.size() / iSigPageNumber;
                }
                else {
                    allPage = oInputData.size() / iSigPageNumber + 1;
                }
                int iRecNum = oInputData.size();
                {
                    int i = absolutePosition;

                    if ( (i + 1) % iSigPageNumber == 0) {
                        this.iPageNo = (i + 1) / iSigPageNumber;
                        if (iPageNo == 0) {
                            iPageNo = 1;
                        }
                    }
                    else {
                        this.iPageNo = i / iSigPageNumber + 1;
                    }
                    if ( (i + 1) % iSigPageNumber != 0) {
                        iRowNo = (i + 1) % iSigPageNumber;
                    }
                    else {
                        iRowNo = iSigPageNumber;
                    }
                }

                for (int i = (this.iPageNo - 1) * this.iSigPageNumber;
                     i < this.iPageNo * this.iSigPageNumber && i < iRecNum; i++) {
                    listV.add(oInputData.get(i));
                }
            }
            this.iPageNumber = allPage;
            if (this.iPageNumber == 0) {
                this.iPageNo = 0;
            }

        }
        catch (Exception e) {
            Log.error("the exception is " + e);
        }
        return listV;
    }

    public int getIPageNo() {
        return iPageNo;
    }

    public int getIPageNumber() {
        return iPageNumber;
    }

    public int getIRowNo() {
        return iRowNo;
    }

}
