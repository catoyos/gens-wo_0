package data_storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class StorageIO {
	public static final String DEFAULT_ROOT_FOLDER = "H:\\wsfiles\\gens-wo\\files"; //TODO DEBUG
//	public static final String DEFAULT_ROOT_FOLDER = "files";
	public static final String DEFAULT_ENCODING = "ISO-8859-1";
	
	public static String getDefaultFolderPath(String id){
		return DEFAULT_ROOT_FOLDER + File.separator + id;
	}
	
	public static String getDefaultFilePath(String fileroot, String filename){
		return fileroot + File.separator + filename;
	}

	public static void printRecordsToFile(List<String> records, File file)
			throws IOException {
		file.getParentFile().mkdirs();
		if (!records.isEmpty()) {
			int bufsz = records.size();
			if (bufsz == 0) {
				;
			} else if (bufsz <= 2) {
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
