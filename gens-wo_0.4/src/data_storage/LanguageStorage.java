package data_storage;

import java.util.List;

import model.Language;
import model.Storable.StorableType;

public class LanguageStorage extends AbstractStorage<Language> {
	// private HashMap<String, Language> depot;

	protected LanguageStorage(String id) {
		super(id, StorableType.LANGUAGE, "langs.dat");
		// this.depot = new HashMap<String, Language>(5);
	}

	protected Language generateNewItem(String zoneID) {
		Language res = getClearItem();
		Language.generateNewLanguage(res, zoneID);
		insertItem(res);
		return res;
	}

	@Override
	protected int getNItems() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<String> getItemIdsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Language> getItemsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Language getClearItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Language getItemById(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Language> getItemsById(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean exists(String itemid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isInMemory(String itemid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isInFile(String itemid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void finish() {
		// TODO Auto-generated method stub

	}

}
