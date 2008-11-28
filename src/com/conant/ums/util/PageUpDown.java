package com.conant.ums.util;

import java.util.*;



public class PageUpDown {
    public PageUpDown() {
    }

    //������¼��������
    private int iRowNo = 0;
    //��ǰҳ
    private int iPageNo = 0;
    //��ҳ��
    private int iPageNumber = 0;
    //ÿҳ��ʾ����
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

    //�����鼯����ȡ���Ӽ�, ���б��Ϸ���
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

    //������������ȡ���Ӽ�, ���б��Ϸ���
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

    //������������ȡ������ָ����¼���Ӽ��ϣ������б��Ϸ���
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
                //���ɾ���ĵ�һ���򷭵���ҳ������궨λ�����һ��
                if (iRowno == 1 && iPageNo != 1) {
                    iPageNo = iPageNo - 1;
                    this.iRowNo = iSigPageNumber;
                }
                else if (iRowno == 1 && iPageNo == 1) {
                    iRowNo = 1;
                    //�����궨λ����һ��
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
    // ��������Ҫ����ȷ��������¼��ʱ��,���Ķ�λ.
    // �������:
    //             oInputData: Ϊ����Ҫ��ʾ�ļ�¼
    //             curLineNo:  �����ļ�¼�����м�¼�еľ���λ��(ϵͳ��Ҫ�����ͣ���ڴ�����¼��.
    //                         �˴���Ҫ��������ÿҳ�е����λ�ü�������һҳ)
    // ���ز���:
    //             List:       �����м�¼���޳����Ǳ�ҳ�ļ�¼,ֻ���ص�ҳ������.
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
