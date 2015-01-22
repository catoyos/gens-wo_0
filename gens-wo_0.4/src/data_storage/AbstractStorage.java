package data_storage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Storable;
import model.Storable.StorableType;

class CustomIDStr implements Comparable<CustomIDStr> {
	
	private static final int PRIME = 31;
	
	final String str;
	private int hashcode;
	private boolean hcset;
	
	public CustomIDStr(String str) {
		this.str = str;
		this.hashcode = hashCode();
	}

	@Override
	public int compareTo(CustomIDStr arg0) {
		return str.compareTo(arg0.str);
	}
	
	@Override
	public int hashCode() {
		if (!hcset) {
			int result = 1;
			if (str == null) {
				result = 0;
			} else {
				for (int i = 0; i < 12 &&  i < str.length(); i++) {
					result = PRIME * result + str.charAt(i);
				}
			}
			hashcode = result;
			hcset = true;
		}
		return hashcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CustomIDStr other = (CustomIDStr) obj;
		if (hashcode != obj.hashCode()) {
			return false;
		}
		if (str == null) {
			if (other.str != null) {
				return false;
			}
		} else if (!str.equals(other.str)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return str;
	}	
}

public abstract class AbstractStorage<T extends Storable> {
	
	
	public final StorableType TYPE;
	protected final String id;
	protected String fileroot;
	private File mainfile;

	protected AbstractStorage(String id, StorableType type, String filename) {
		this.id = id;
		this.TYPE = type;
		this.fileroot = StorageIO.getDefaultFolderPath(id);
		this.mainfile = new File(StorageIO.getDefaultFilePath(fileroot, filename));
	}

	/**
	 * @return
	 */
	protected abstract int getNItems();

	/**
	 * @return
	 */
	protected abstract List<String> getItemIdsList();

	/**
	 * @return
	 */
	protected abstract List<T> getItemsList();

	/**
	 * @return
	 */
	protected abstract T getClearItem();

	/**
	 * @param itemid
	 * @return
	 */
	protected abstract T getItemById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	protected abstract List<T> getItemsById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	protected abstract T getItemFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	protected abstract List<T> getItemsFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	protected abstract void insertItem(T item);

	/**
	 * @param itemid
	 * @return
	 */
	protected abstract boolean exists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	protected abstract boolean isInMemory(String itemid);
	
	/**
	 * @param itemid
	 * @return
	 */
	protected abstract boolean isInFile(String itemid);

	/**
	 * 
	 */
	protected abstract void finish();

	protected void printToFile(List<String> records) throws IOException {
		StorageIO.printRecordsToFile(records, mainfile);
	}

	protected String loadFileToString() throws IOException {
		return StorageIO.getFileContent(mainfile);
	}

}