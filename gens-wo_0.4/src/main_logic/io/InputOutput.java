package main_logic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import data_storage.StorageIO;

public class InputOutput {

//	public static final String DEFAULT_ROOT_FOLDER = StorageIO.DEFAULT_ROOT_FOLDER;
//	public static final String DEFAULT_ROOT_FOLDER = "H:\\wsfiles\\gens-wo\\files"; //TODO DEBUG
//	public static final String DEFAULT_ROOT_FOLDER = "files";
	public static final String DEFAULT_ENCODING = StorageIO.DEFAULT_ENCODING;
	
	public static int askForOption(IOMenu iom) {
		printmenu(iom);
		String opt = readline();
		int optn = 0;
		boolean sal = false;
		Iterator<IOMenuOption>it=iom.getOptions().iterator();
		while (!sal) {
			while (it.hasNext() && !sal) {
				IOMenuOption iomo = it.next();
				if (iomo.key.equals(opt)) {
					sal = true;
					optn = iomo.idx;
				}
			}
			if (!sal) {
				println(iom.getErrorMessage());
				printmenu(iom);
				opt = readline();
				it = iom.getOptions().iterator();
			}
		}
		return optn;
	}
	
	public static String getDefaultFolderPath(String id){
//		return DEFAULT_ROOT_FOLDER + File.separator + id;
		return getDefaultFolderPath(StorageIO.DEFAULT_ROOT_FOLDER, id);
	}

	public static String getDefaultFolderPath(String folder, String id) {
		return folder + File.separator + id;
	}
	
	public static String getDefaultFilePath(String fileroot, String filename){
		return fileroot + File.separator + filename;
	}

	public static void println() {
		System.out.println();
	}

	public static void println(Object obj) {
		System.out.println(obj.toString());
	}
	
	public static void printf(String format, Object...args ) {
		System.out.format(format, args);
	}
	
	public static void printlist(Collection<Object> objs) {
		if (objs != null && objs.size() > 0) {
			for (Object obj : objs) {
				System.out.println(obj);
			}
		}
	}
	
	public static void printlist(List<String> objs) {
		if (objs != null && objs.size() > 0) {
			for (Object obj : objs) {
				System.out.println(obj);
			}
		}
	}
	
	public static void printmenu(IOMenu iom) {
		println(iom.getTitulo());
		for (IOMenuOption iomo : iom.getOptions()) {
			println(iomo);
		}
	}

	public static String readline() {
		String res = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			res = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static double readnumber() {
		try {
			return Double.parseDouble(readline());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public static char readchar() {
		try {
			return readline().charAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return '?';
		
	}

	public static boolean readboolean(char y) {
		try {
			String line = readline();
			return line.toUpperCase().charAt(0) == Character.toUpperCase(y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void printRecordsToFile(List<String> records, File file)
			throws IOException {
		file.getParentFile().mkdirs();
		if (!records.isEmpty()) {
			int bufsz = records.size();
			if (bufsz <= 2) {
				bufsz *= (records.get(0).length() * 2);
			} else if (bufsz > 20000) {
				bufsz += 10;
				bufsz *= ((records.get(0).length() + records.get(1).length() + 10) / 2);
			} else {
				bufsz = 25000 * ((records.get(0).length() + records.get(1).length() + 10) / 2);
			}
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(file), bufsz);

				for (String record : records) {
					writer.write(record);
					writer.newLine();
				}
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				writer.close();
			}
		}
	}

	public static String getFileContent(File file) throws IOException {
		return getFileContent(file.toURI().toURL());
	}
	
	public static String getFileContent(URL url) throws IOException {
		URLConnection conn = url.openConnection();
		String encoding = conn.getContentEncoding();
		if (encoding == null) {
			encoding = DEFAULT_ENCODING;
		}
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
		} catch (UnsupportedEncodingException e) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		String sb = "";
		try {
			StringBuilder stbld = new StringBuilder(256);
			String line = br.readLine();
			if (line != null){
				stbld.append(line);
				line = br.readLine();
				while (line != null) {
					stbld.append('\n');
					stbld.append(line);
					line = br.readLine();
				}
			}
			sb = stbld.toString();
		} finally {
			br.close();
		}
		return sb;
	}
	
}
