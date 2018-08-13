package com.leidengyun.common;

import java.text.NumberFormat;

public class StringUtis {

	public static Object formatZero(double num, double magNitude) {
		if ((43691.0== num) || (43947.0 == num)) {
			return "-";
		}
		return NumberFormat.getInstance().format(num*magNitude);
	}
}
