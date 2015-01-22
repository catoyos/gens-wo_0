package data_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Storable.StorableType;
import model.World;

public class WorldStorage extends AbstractStorage<World> {
	
	private HashMap<String, World> depot;

	protected WorldStorage(String id) {
		super(id, StorableType.WORLD, "worlds.dat");
		this.depot = new HashMap<String, World > (5);
	}

	protected World generateNewItem(String uniID) {
		World res = World.generateNewWorld(getClearItem(), uniID);
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
	protected List<World> getItemsList() {
		return new LinkedList<World>(depot.values());
	}

	@Override
	protected World getClearItem() {
		return new World();
	}

	@Override
	protected World getItemById(String itemid) {
		return depot.get(itemid);
	}

	@Override
	protected List<World> getItemsById(List<String> itemids) {
		List<World> res = new LinkedList<World>();
		for (String wid : itemids) {
			res.add(getItemById(wid));
		}
		return res;
	}

	@Override
	protected World getItemFromFile(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<World> getItemsFromFile(List<String> itemids) {
		// TODO Auto-generated method stub
		List<World> res = new LinkedList<World>();
		for (String wid : itemids) {
			res.add(getItemFromFile(wid));
		}
		return res;
	}

	@Override
	protected void insertItem(World item) {
		depot.put(item.getWorldID(), item);
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
			for (World world : depot.values()) {
				records.add(world.toFileString());
			}
			super.printToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "WorldStorage [" + depot.size() + " worlds]"
				+"\n\t"+depot.entrySet().toString();
	}
}