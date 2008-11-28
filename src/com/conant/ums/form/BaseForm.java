package com.conant.ums.form;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;
import com.conant.ums.util.PageUpDown;

public class BaseForm
    extends ActionForm {
    private String forward;
    private int curPageNo;
    private int totalPage;
    private List allPageNo;
    private String preNextPageInfo;
    private String sortCol;
    private String sortOrder;

    //记录光标目前所在的位置,for cursor postion
    private int curLineNo;

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest httpServletRequest) {
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public int getCurPageNo() {
        return curPageNo;
    }

    public void setCurPageNo(int curPageNo) {
        this.curPageNo = curPageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getAllPageNo() {
        return allPageNo;
    }

    public void setAllPageNo(List allPageNo) {
        this.allPageNo = allPageNo;
    }

    public void setPageInfo() {

    }

    public String getPreNextPageInfo() {
        return preNextPageInfo;
    }

    public void setPreNextPageInfo(String preNextPageInfo) {
        this.preNextPageInfo = preNextPageInfo;
    }

    public void generatePageInfo(PageUpDown pageUpDown) {
        this.setTotalPage(pageUpDown.getIPageNumber());
        this.setCurPageNo(pageUpDown.getIPageNo());
        //added for cursor postion
        if (pageUpDown.getIRowNo() > 0) {
            this.setCurLineNo(pageUpDown.getIRowNo());
        }
        /*
                 //set page select box
                 List ltPageNo = new ArrayList();
                 for(int i=0;i<pageUpDown.getIPageNumber();i++){
            int iPageNo = i+1;
            SelectOption pageOption = new SelectOption(String.valueOf(iPageNo),"第"+iPageNo+"页");
            ltPageNo.add(pageOption);
                 }
                 this.setAllPageNo(ltPageNo);
                 //set previous and next page info(including url)
                 String sPreNextPageInfo = "";
                 if(pageUpDown.getIPageNo()==1){
            sPreNextPageInfo += "<input type=\"button\" value=\"上一页\" class=\"button\" disabled>";
                 }else{
            sPreNextPageInfo += "<input type=\"button\" value=\"上一页\" class=\"button\" onclick=\"preNextPage("+ (pageUpDown.getIPageNo()-1) +")\">";
                 }
                 if(pageUpDown.getIPageNo()==pageUpDown.getIPageNumber()){
            sPreNextPageInfo += "<input type=\"button\" value=\"下一页\" class=\"button\" disabled>";
                 }else{
            sPreNextPageInfo += "<input type=\"button\" value=\"下一页\" class=\"button\" onclick=\"preNextPage("+ (pageUpDown.getIPageNo()+1) +")\">";
                 }
                 this.setPreNextPageInfo(sPreNextPageInfo);*/
    }

    public String getSortCol() {
        return sortCol;
    }

    public void setSortCol(String sortCol) {
        this.sortCol = sortCol;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCurLineNo() {
        return curLineNo;
    }

    public void setCurLineNo(int curLineNo) {
        this.curLineNo = curLineNo;
    }

}
