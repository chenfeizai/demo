package com.demo.model.simple;

import java.io.Serializable;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

public class Testtable implements Serializable {

	
	private static final long serialVersionUID = 1432293432303832894L;
	private int id;
    private String username;
    private String userpass;
    private String orderid;
    /*private String mobile;
    private String planid;*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
/*	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}*/
	@Override
	public String toString() {
		return "Testtable [id=" + id + ", username=" + username + ", userpass=" + userpass + ", orderid=" + orderid
				+ "]";
	}
}
