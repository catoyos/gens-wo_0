package model;

import java.util.Collection;
import java.util.List;

import model.utils.StringUtils;

public class City extends Storable {

	public static enum Direction { NORTH, EAST, SOUTH, WEST };

	private static long newIDn = (long) (Math.random() * StringUtils.ID_N_25_3);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }

	private String cityID;
	private String parentZoneID;
	private List<String> individuals;
	private String[] adjacentCityIDs;
	
	public City() {
		super(StorableType.CITY);
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getParentZoneID() {
		return parentZoneID;
	}

	public void setParentZoneID(String parentZoneID) {
		this.parentZoneID = parentZoneID;
	}

	public List<String> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<String> individuals) {
		this.individuals = individuals;
	}

	public void addIndividualId(String sIndividualID) {
		individuals.add(sIndividualID);
	}

	public void addIndividual(Individual zIndividual) {
		individuals.add(zIndividual.getIndividualID());
	}

	public void addAllIndividualIDs(Collection<String> sIndividualIDs) {
		individuals.addAll(sIndividualIDs);
	}

	public void addAllIndividuals(Collection<Individual> zIndividuals) {
		for (Individual ind : zIndividuals) {
			individuals.add(ind.getIndividualID());
		}
	}

	public boolean containsIndividualID(String sIndividualID) {
		return individuals.contains(sIndividualID);
	}

	public boolean containsIndividual(Individual zIndividual) {
		return individuals.contains(zIndividual.getIndividualID());
	}

	public boolean isEmptyIndividualIDs() {
		return individuals.isEmpty();
	}

	public boolean removeIndividualID(String sIndividualID) {
		return individuals.remove(sIndividualID);
	}

	public boolean removeIndividual(Individual zIndividual) {
		return individuals.remove(zIndividual.getIndividualID());
	}

	public int getNIndividuals() {
		return individuals.size();
	}

	public String[] getAdjacentCityIDs() {
		return adjacentCityIDs;
	}

	public void setAdjacentCityIDs(String[] adjacentCityIDs) {
		this.adjacentCityIDs = adjacentCityIDs;
	}
	
	public void setAdjacentCities(City[] acs){
		for (int i = 0; i < adjacentCityIDs.length &&  i < acs.length; i++) {
			adjacentCityIDs[i] = acs[i].cityID;
		}
	}

	public void setAdjacentCity(City acs, Direction dir){
		setAdjacentCityID(acs.cityID, dir);
	}
	
	public void setAdjacentCityID(String acs, Direction dir){
		int res = -1;
		switch (dir) {
		case NORTH: res = 0;
			break;
		case EAST: res = 1;
			break;
		case SOUTH: res = 2;
			break;
		case WEST: res = 3;
			break;
		}
		try {
			adjacentCityIDs[res] = acs;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAdjacentCityID(Direction dir){
		int res = -1;
		switch (dir) {
		case NORTH: res = 0;
			break;
		case EAST: res = 1;
			break;
		case SOUTH: res = 2;
			break;
		case WEST: res = 3;
			break;
		default:return null;
		}
		return adjacentCityIDs[res];
	}
	
	@Override
	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}

}
