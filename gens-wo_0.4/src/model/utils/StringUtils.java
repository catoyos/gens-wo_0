package model.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
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
		return new String(res).intern();
	}

	public static String generateIndividualId(long i, String zid) {
		StringBuffer res = new StringBuffer((zid == null) ? ("ZZ") : (zid));
		res.append(String.format("%7s", "" + i).replace(' ', '0'));
		return res.toString().intern();
	}

	public static String listToCompactString(List<String> strings) {
		return listToCompactString(strings, ",", null);
	}
	
	public static String listToCompactString(List<String> strings, String sep) {
		return listToCompactString(strings, sep, null);
	}
	
	public static String listToCompactString(List<String> strings, String sep, String nullValue) {
		if (strings == null || strings.isEmpty()) {
			return "".intern();
		} else if (strings.size() == 1) {
			String res = strings.get(0);
			if (res != null) {
				return res;
			} else if (nullValue != null){
				return nullValue;
			} else {
				return "".intern();
			}
		} else {
			StringBuilder sb = new StringBuilder();
			if (strings.size() > 0) {
				String str = strings.get(0);
				if (str != null) {
					sb.append(str);
				} else if (nullValue != null){
					sb.append(nullValue);
				}
				for (int i = 1; i < strings.size(); i++) {
					sb.append(sep);
					str = strings.get(i);
					if (str != null) {
						sb.append(str);
					} else if (nullValue != null){
						sb.append(nullValue);
					}
				}
			}
			return sb.toString();
		}
	}
	
	public static List<String> compactStringToList(String str) {
		return compactStringToList(str, ",", null);
	}
	
	public static List<String> compactStringToList(String str, String sep) {
		return compactStringToList(str, sep, null);
	}
	
	public static List<String> compactStringToList(String str, String sep, String nullValue) {
		List<String> res = new LinkedList<String>();
		if (str != null && !"".equals(str)) {
			String[] data = str.split(sep);
			for (String string : data) {
				if (nullValue != null && nullValue.equals(string))
					res.add(null);
				else
					res.add(string.intern());
			}
		}
		return res;
	}
	
}
