package data_storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.City;
import model.Individual;
import model.Storable.StorableType;

public class IndividualStorage extends AbstractStorage<Individual> {
	
	private HashMap<String, Individual> depot;
	
	protected IndividualStorage(String id) {
		super(id, StorableType.INDIVIDUAL, "individuals.dat");
		this.depot = new HashMap<String, Individual>(100);
	}

	protected Individual generateNewItem(City city, float birthDate) {
		String strCity = city.getCityID();
		Individual res = getClearItem();
		Individual.generateNewIndividual(res, strCity, birthDate);
		insertItem(res);
		return res;
	}

	public Individual generateNewItem(Individual father, Individual mother,
			float birthDate) {
		Individual res = getClearItem();
		Individual.generateIndividualFromParents(res, father, mother, birthDate);
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
	protected List<Individual> getItemsList() {
		return new LinkedList<Individual>(depot.values());
	}

	@Override
	protected Individual getClearItem() {
		return new Individual();
	}

	@Override
	protected Individual getItemById(String itemid) {
		return depot.get(itemid);
	}

	@Override
	protected List<Individual> getItemsById(List<String> itemids) {
		List<Individual> res = new LinkedList<Individual>();
		for (String wid : itemids) {
			res.add(getItemById(wid));
		}
		return res;
	}

	@Override
	protected Individual getItemByIdFromMemory(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Individual> getItemsByIdFromMemory(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Individual getItemByIdFromFile(String itemid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Individual> getItemsByIdFromFile(List<String> itemids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void insertItem(Individual item) {
		depot.put(item.getIndividualID(), item);
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
			for (Individual individual : depot.values()) {
				records.add(individual.toFileString());
			}
			super.printToFile(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "IndividualStorage [" + depot.size() + " individuals]"
				+"\n\t"+depot.entrySet().toString();
	}
}
