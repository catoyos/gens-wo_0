package data_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Storable.StorableType;
import model.Zone;

public class ZoneStorage extends AbstractStorage<Zone> {
	private HashMap<String, Zone> depot;

	protected ZoneStorage(String id) {
		super(id, StorableType.ZONE, "zones.dat");
		this.depot = new HashMap<String, Zone>(5);
	}

	protected Zone generateNewItem(String worldID) {
		Zone res = Zone.generateNewZone(getClearItem(), worldID);
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
	protected List<Zone> getItemsList() {
		return new LinkedList<Zone>(depot.values());
	}

	@Override
	protected Zone getClearItem() {
		return new Zone();
	}

	@Override
	protected Zone getItemById(String itemid) {
		return depot.get(itemid);
	}

	@Override
	protected List<Zone> getItemsById(List<String> itemids) {
		List<Zone> res = new LinkedList<Zone>();
		for (String wid : itemids) {
			res.add(getItemById(wid));
		}
		return res;
	}

	@Override
	protected Zone getItemFromFile(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Zone> getItemsFromFile(List<String> itemids) {
		// TODO Auto-generated method stub
		List<Zone> res = new LinkedList<Zone>();
		for (String wid : itemids) {
			res.add(getItemFromFile(wid));
		}
		return res;
	}

	@Override
	protected void insertItem(Zone item) {
		depot.put(item.getZoneID(), item);
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
			for (Zone zone : depot.values()) {
				records.add(zone.toFileString());
			}
			super.printToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "ZoneStorage [" + depot.size() + " zones]"
				+"\n\t"+depot.entrySet().toString();
	}
}