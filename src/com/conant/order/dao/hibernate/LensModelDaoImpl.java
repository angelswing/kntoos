/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao.hibernate;

import com.conant.order.dao.LensModelDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Administrator
 */
public class LensModelDaoImpl implements LensModelDao {

    private static Logger log = Logger.getLogger("LensModelDaoImpl", Logger.DEBUG, true);
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) throws ProcessException {
        this.template = template;
    }

    public List getLensModel() throws ProcessException {
        List list = template.find("from LensModel");
        return list;
    }
}
