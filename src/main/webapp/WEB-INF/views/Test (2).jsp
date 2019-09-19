<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小雨点状态模拟推单</title>
</head>
<body>
	<form method="post" onsubmit="return toVaild()" action="/hpayDFPaySupport/dopush">
		输入订单号：<input type=text id="order_id" name="order_id" /> </br>
		</br> 输入扣款期数：<input type=text name="debit_num" /> </br>
		</br> 输入扣款状态：<input type=text name="debitstatus" /> SUCESS 成功 FAIL 失败 </br>
		</br> 输入债转起始期数：<input type=text name="start_num" /> </br>
		</br> <input type=submit value="开始放款" /> <input type=submit value="开始代扣" />
		<input type=submit value="开始债转" />

	</form>
</body>

<script language="javascript">
	function toVaild() {
		var val = document.getElementById("order_id").value;
		if (!val) {
			alert("订单号为空，不能进行提交");
			return false;
		} else {
			return true;
		}
	}
</script>
</html>