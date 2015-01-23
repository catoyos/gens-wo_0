package data_storage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Storable;
import model.Storable.StorableType;

public abstract class AbstractStorage<T extends Storable> {
	
	
	public final StorableType TYPE;
	protected final String id;
	protected String fileroot;
	private File mainfile;

	/**
	 * @param id
	 * @param type	type of Storable to store, as defined in Storable.StorableType
	 * @param filename	path to the backing file INSIDE the default folder
	 */
	protected AbstractStorage(String id, StorableType type, String filename) {
		this.id = id;
		this.TYPE = type;
		this.fileroot = StorageIO.getDefaultFolderPath(id);
		this.mainfile = new File(StorageIO.getDefaultFilePath(fileroot, filename));
	}


	/**
	 * @return	number of distinct items stored
	 */
	protected abstract int getNItems(); //TODO separar memoria/archivo

	/**
	 * @return	list of the IDs of all the stored items
	 */
	protected abstract List<String> getItemIdsList(); //TODO separar memoria/archivo

	/**
	 * @return	list of all the items
	 */
	protected abstract List<T> getItemsList(); //TODO separar memoria/archivo

	/**
	 * @return	a clear instance of T
	 */
	protected abstract T getClearItem();

	/**
	 * Returns an item by first looking for it in memory, and then,
	 * if not present, in the backing file
	 * 
	 * @param itemid	ID of the item to return
	 * @return	item with itemid ID or null if not stored
	 */
	protected abstract T getItemById(String itemid);

	/**
	 * Returns a list of items in the same order as the list of IDs,
	 * by first looking for them in memory, and then, if not present,
	 * in the backing file
	 * 
	 * @param itemids	list of ID of the items to return
	 * @return	list of items (in the same order as itemids), or 
	 * 	null for the ones not stored
	 */
	protected abstract List<T> getItemsById(List<String> itemids);

	/**
	 * Returns an item present in memory
	 * 
	 * @param itemid	ID of the item to return
	 * @return	item with itemid ID or null if not stored
	 */
	protected abstract T getItemByIdFromMemory(String itemid);

	/**
	 * Returns a list of items in the same order as the list of IDs,
	 * present in memory
	 * 
	 * @param itemids	list of ID of the items to return
	 * @return	list of items (in the same order as itemids), or 
	 * 	null for the ones not stored
	 */
	protected abstract List<T> getItemsByIdFromMemory(List<String> itemids);

	/**
	 * Returns an item from the backing file, ignoring whether or not
	 * it's present in memory. If it IS in memory, this does not overwrite
	 * it
	 * 
	 * @param itemid	ID of the item to return
	 * @return	item with itemid ID or null if not stored
	 */
	protected abstract T getItemByIdFromFile(String itemid);

	
	/**
	 * Returns a list of items in the same order as the list of IDs,
	 * from the backing file, ignoring whether or not they are present 
	 * in memory. If they ARE in memory, this does not overwrite them
	 * 
	 * @param itemids	list of ID of the items to return
	 * @return	list of items (in the same order as itemids), or 
	 * 	null for the ones not stored
	 */
	protected abstract List<T> getItemsByIdFromFile(List<String> itemids);

	/**
	 * Inserts an item
	 * 
	 * @param item
	 */
	protected abstract void insertItem(T item);

	/**
	 * Checks if an item exists either in memory or in the backing file
	 * 
	 * @param itemid
	 * @return
	 */
	protected abstract boolean exists(String itemid);

	/**
	 * Checks if an item exists either in memory
	 * 
	 * @param itemid
	 * @return
	 */
	protected abstract boolean isInMemory(String itemid);
	
	/**
	 * Checks if an item exists either in the backing file
	 * 
	 * @param itemid
	 * @return
	 */
	protected abstract boolean isInFile(String itemid);

	/**
	 * 
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