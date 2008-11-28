/**
 * QuerierResult.java
 * 2008-11-22
 * Administrator
 */
package com.conant.order.vo;

import java.util.List;

/**
 * @author Administrator
 * 
 */
public class QuerierResult
{
	private List voList;
	private int totalCount;

	public List getVoList()
	{
		return voList;
	}

	public void setVoList(List voList)
	{
		this.voList = voList;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
}
