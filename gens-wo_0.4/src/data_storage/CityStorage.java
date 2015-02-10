package data_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.City;
import model.Storable.StorableType;

public class CityStorage extends AbstractStorage<City> {
	
	private HashMap<String, City> depot;
	
	protected CityStorage(String id) {
		super(id, StorableType.CITY, "cities.dat");
		this.depot = new HashMap<String, City>(30);
	}

	protected City generateNewItem(String worldID, String parentZoneID) {
		City res = getClearItem();
		City.generateNewCity(res, worldID, parentZoneID);
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
	protected List<City> getItemsList() {
		return new LinkedList<City>(depot.values());
	}

	@Override
	protected City getClearItem() {
		return new City();
	}

	@Override
	protected City getItemById(String itemid) {
		return depot.get(itemid);
	}

	@Override
	protected List<City> getItemsById(List<String> itemids) {
		List<City> res = new LinkedList<City>();
		for (String wid : itemids) {
			res.add(getItemById(wid));
		}
		return res;
	}

	@Override
	protected City getItemByIdFromMemory(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<City> getItemsByIdFromMemory(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected City getItemByIdFromFile(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<City> getItemsByIdFromFile(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void insertItem(City item) {
		depot.put(item.getCityID(), item);
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
			for (City city : depot.values()) {
				records.add(city.toFileString());
			}
			super.printToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "CityStorage [" + depot.size() + " cities]"
				+"\n\t"+depot.entrySet().toString();
	}
}
