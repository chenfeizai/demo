package com.demo.testxydcallbacknoticy;

public class putzero {

	String aa="";

	public String setputzero(String returnsql) {

		char[] ar = returnsql.toCharArray();
		for (int i = 0; i < ar.length; i++) {
			if (i < ar.length - 2) {
				aa += ar[i];
			}
		}
		return aa;
	}

}
