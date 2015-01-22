package model.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StringUtils {
	public static final int ID_N_25_2 = 625;
	public static final int ID_N_25_3 = 15625;
	public static final int ID_N_25_4 = 390625;
	public static final int ID_N_25_5 = 9765625;

	public static final DecimalFormat df = new DecimalFormat("#.###", new DecimalFormatSymbols(new Locale("en")));
	
	public static String generateId(int n, int l) {
		if (l < 1)
			l = 1;
		int r = 25;
		char ini = 'A';
		int b = (int) Math.pow(r, l);
		int a = n % b;
		char[] res = new char[l];
		for (int i = 0; i < res.length; i++) {
			b = (int) Math.pow(r, l - 1 - i);
			res[i] = (char) (ini + a / b);
			a %= b;
		}
		return new String(res);
	}

	public static String listToCompactString(List<String> strings) {
		return listToCompactString(strings, ",");
	}
	
	public static String listToCompactString(List<String> strings, String sep) {
		StringBuilder sb = new StringBuilder();
		
		if (strings.size() > 0) {
			String str = strings.get(0);
			if (str != null) {
				sb.append(str);
			}
			for (int i = 1; i < strings.size(); i++) {
				sb.append(sep);
				str = strings.get(i);
				if (str != null) {
					sb.append(str);
				}
			}
		}
		
		return sb.toString();
	}
	
	public static List<String> compactStringToList(String str) {
		return compactStringToList(str, ",");
	}
	
	public static List<String> compactStringToList(String str, String sep) {
		return Arrays.asList(str.split(sep));
	}
	
}
