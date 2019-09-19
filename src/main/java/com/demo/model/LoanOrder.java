package com.demo.model;

import java.io.Serializable;

public class LoanOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 810302994187002518L;

	private String id;

	private String loanMerName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoanMerName() {
		return loanMerName;
	}

	public void setLoanMerName(String loanMerName) {
		this.loanMerName = loanMerName;
	}

	@Override
	public String toString() {
		return "id=" + id + ",loanMerName=" + loanMerName;
	}

}
