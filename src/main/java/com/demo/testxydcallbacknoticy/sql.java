package com.demo.testxydcallbacknoticy;

public class sql {
	String sql;

	public void setselLoanOrder(String order_id) {
		sql = "select * from t_dpay_loan_order where id ='" + order_id + "'";
	}

	public void setselRepayPlan(String order_id, String installment_num) {
		sql = "select * from t_dpay_loan_repay_plan where order_id ='" + order_id + "' and installment_num ='"
				+ installment_num + "';";
	}

	public void setselSUMRepayPlan(String order_id, String row) {
		sql = "select  sum(" + row + ") as sum  from t_dpay_loan_repay_plan where order_id ='" + order_id + "'";
	}
	// 第一笔减2个月，第二笔减2个月，其他不变
	public void setselRepayPlanacdate(String order_id, int installment_num) {
	    sql="select IF(installment_num=1,DATE_SUB(now(),interval 2 MONTH) ,IF(installment_num=2,DATE_SUB(now(),interval 1 MONTH),ac_date)) as ac_date from t_dpay_loan_repay_plan where order_id ='"
					+ order_id + "' and installment_num ='" + installment_num+"'";
	}
	public String getsql() {
		return sql;

	}

}
