package com.leidengyun.sjptn.common;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StringUtis {

	public static Object formatZero(double num, double magNitude) {
		if ((43691.0D * magNitude == num) || (43947.0D * magNitude == num)) {
			return "-";
		}
		return NumberFormat.getInstance().format(num*magNitude);
	}
	
	
	public static void removeDuplicate(List list) {
		Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        //System.out.println(" remove duplicate " + list);
	}
}
