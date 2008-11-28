package com.conant.order.util;

import java.io.*;

/**
 * <p>Title: Online-Order System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author Martin
 * @version 1.0
 */

public class ProcessException extends Exception implements Serializable {

    private String errorCode;
    private Object param;

    /**
     * @param errorId
     * @roseuid 426327650182
     */
    public ProcessException(int errorCode, Object param) {
        super(errorCode + ": " + (String) param);
        this.errorCode = String.valueOf(errorCode);
        setParam(param);
    }

    public ProcessException(int errorCode) {
        super(errorCode + ": no reason!");
        this.errorCode = String.valueOf(errorCode);
    }

    /**
     * ��ȡ�쳣�Ĵ�����
     * ������ӦΪ6λ������������ɵ��ַ�����������ǣ��򷵻�null
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    } //end getErrorCode

    /**
     * ��ȡ�쳣�ľ������ԭ��
     * @return
     */
    public String getErrorReason() {
        String reason = null;
        String errorCode = getErrorCode();
        if (errorCode != null) {
            reason = ExceptionHelper.getExceptionReason(errorCode);
        } else {
            reason = "Unknown Exception Reason.";
        }
        return reason;
    } //end getErrorReason

    public void setParam(Object param) {
        this.param = param;
    }
}
