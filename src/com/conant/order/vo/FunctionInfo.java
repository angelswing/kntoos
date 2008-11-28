package com.conant.order.vo;

public class FunctionInfo {

	private long id;
	private long func_Pid;
	private String func_URL;
	private String func_Name;
	private String func_Desc;
	private int func_Type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFunc_Pid() {
		return func_Pid;
	}

	public void setFunc_Pid(long func_Pid) {
		this.func_Pid = func_Pid;
	}

	public String getFunc_Name() {
		return func_Name;
	}

	public void setFunc_Name(String func_Name) {
		this.func_Name = func_Name;
	}

	public String getFunc_Desc() {
		return func_Desc;
	}

	public void setFunc_Desc(String func_Desc) {
		this.func_Desc = func_Desc;
	}

	public int getFunc_Type() {
		return func_Type;
	}

	public void setFunc_Type(int func_Type) {
		this.func_Type = func_Type;
	}

	public String getFunc_URL() {
		return func_URL;
	}

	public void setFunc_URL(String func_URL) {
		this.func_URL = func_URL;
	}

}
