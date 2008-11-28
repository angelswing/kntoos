/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conant.order.dao;

import com.conant.order.util.ProcessException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface LensModelDao {
    List getLensModel() throws ProcessException;
}
