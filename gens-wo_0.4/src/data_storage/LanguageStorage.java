package data_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Language;
import model.Storable.StorableType;

public class LanguageStorage extends AbstractStorage<Language> {
	
	private HashMap<String, Language> depot;

	protected LanguageStorage(String id) {
		super(id, StorableType.LANGUAGE, "langs.dat");
		this.depot = new HashMap<String, Language>(30);
	}

	protected Language generateNewItem(String zoneID) {
		Language res = getClearItem();
		Language.generateNewLanguage(res, zoneID);
		res.modified = true;
		insertItem(res);
		return res;
	}

	@Override
	protected int getNItems() {
		return depot.size();
	}

	@Override
	protected List<String> getItemIdsList() {
		return new LinkedList<String>(depot.keySet());
	}

	@Override
	protected List<Language> getItemsList() {
		return new LinkedList<Language>(depot.values());
	}

	@Override
	protected Language getClearItem() {
		return new Language();
	}

	@Override
	protected Language getItemById(String itemid) {
		return depot.get(itemid);
	}

	@Override
	protected List<Language> getItemsById(List<String> itemids) {
		List<Language> res = new LinkedList<Language>();
		for (String wid : itemids) {
			res.add(getItemById(wid));
		}
		return res;
	}

	@Override
	protected Language getItemByIdFromMemory(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Language> getItemsByIdFromMemory(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Language getItemByIdFromFile(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Language> getItemsByIdFromFile(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void insertItem(Language item) {
		depot.put(item.getLanguageID(), item);
	}

	@Override
	protected boolean exists(String itemid) {
		return isInMemory(itemid) || isInFile(itemid);
	}

	@Override
	protected boolean isInMemory(String itemid) {
		return depot.containsKey(itemid);
	}

	@Override
	protected boolean isInFile(String itemid) {
		return false;
	}

	@Override
	protected void finish() {
		try {
			LinkedList<String> records = new LinkedList<String>();
			for (Language lang : depot.values()) {
				records.add(lang.toFileString());
			}
			super.printToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
