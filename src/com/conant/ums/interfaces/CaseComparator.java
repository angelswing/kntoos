package com.conant.ums.interfaces;

import java.util.*;
import com.conant.ums.data.F130_UserMgt;

public class CaseComparator
    implements Comparator {

    public CaseComparator() {
    }

    public int compare(Object element1, Object element2) {
        int result = 1;

        F130_UserMgt first = (F130_UserMgt) element1;
        F130_UserMgt second = (F130_UserMgt) element2;

        if(first.getLogin_time().compareTo(second.getLogin_time()) > 0){
            result = -1;
        }
        return result;
    }

}

