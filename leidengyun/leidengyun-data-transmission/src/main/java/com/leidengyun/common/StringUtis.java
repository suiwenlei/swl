package com.leidengyun.common;

import java.text.NumberFormat;
import java.util.*;

public class StringUtis {

	public static Object formatZero(double num, double magNitude) {
		if ((43691.0== num) || (43947.0 == num)) {
			return "-";
		}
		return NumberFormat.getInstance().format(num*magNitude);
	}


	public static void removeDuplicate(List list) {
		Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element)) {
				newList.add(element);
			}
        }
        list.clear();
        list.addAll(newList);
	}
}
