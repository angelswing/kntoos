package com.conant.ums.lbean;

import java.sql.*;

import com.conant.ums.db.*;
import com.conant.ums.lbean.*;

/**
 * <p>Copyright:</p>
 * @author:
 * @version 1.0
 */
public class LogoutLBean
    extends BaseLBean {

    public LogoutLBean() {
    }

    public int userLogout(Connection oConn, String user_tag) throws Exception {
        int iRet = 0;

        String sql = "update user_user set is_login = '0' where user_tag = '" +
            user_tag + "'";

        log.debug("update sql :" + sql);

        DbAccess oDba = new DbAccess();
        iRet = oDba.execute(oConn, sql);

        return iRet;
    }
}
