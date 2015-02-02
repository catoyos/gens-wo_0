package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class City extends Storable {

	public static enum Direction { NORTH, EAST, SOUTH, WEST };

	private static long newIDn = Arch.RND.nextInt(StringUtils.ID_N_25_3);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }

	private String cityID;
	private String parentZoneID;
	private List<String> citizens;
	private String[] adjacentCityIDs;
	
	public City() {
		super(StorableType.CITY);
		this.citizens = new LinkedList<String>();
	}
	
	public static void generateNewCity(City res, String worldID, String parentZoneID) {
		res.cityID = worldID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_3), 3);
		res.parentZoneID = parentZoneID;
		
		if (res.citizens == null) {
			res.citizens = new LinkedList<String>();
		} else {
			res.citizens.clear();
		}
		res.adjacentCityIDs = new String[4];
	}
	
	public static void generateFromString(City res, String string) {
		String[] data = string.split(",");
		res.cityID = data[0];
		res.parentZoneID = data[1];

		if (data.length > 1 && !"".equals(data[2])) {
			res.citizens = Arrays.asList(data[2].split("@"));
		} else if (res.citizens == null) {
			res.citizens = new LinkedList<String>();
		} else {
			res.citizens.clear();
		}
		
		res.adjacentCityIDs = new String[4];
		if (data.length > 2) {
			String[] cts = data[3].split("@");
			for (int i = 0; i < cts.length && i < res.adjacentCityIDs.length; i++) {
				if (!"-".equals(cts[i])) {
					res.adjacentCityIDs[i] = cts[i];
				}
			}
		}
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

	public List<String> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<String> individuals) {
		this.citizens = individuals;
	}

	public void addCitizen(String sIndividualID) {
		citizens.add(sIndividualID);
	}

	public void addCitizen(Individual zIndividual) {
		citizens.add(zIndividual.getIndividualID());
	}

	public boolean containsCitizen(String sIndividualID) {
		return citizens.contains(sIndividualID);
	}

	public boolean containsCitizen(Individual zIndividual) {
		return citizens.contains(zIndividual.getIndividualID());
	}

	public boolean isEmptyCitizens() {
		return citizens.isEmpty();
	}

	public boolean removeCitizen(String sIndividualID) {
		return citizens.remove(sIndividualID);
	}

	public boolean removeCitizen(Individual zIndividual) {
		return citizens.remove(zIndividual.getIndividualID());
	}

	public int getNCitizens() {
		return citizens.size();
	}

	public String[] getAdjacentCityIDs() {
		return adjacentCityIDs;
	}

	protected void setAdjacentCityIDs(String[] adjacentCityIDs) {
		this.adjacentCityIDs = adjacentCityIDs;
	}
	
	protected void setAdjacentCities(City[] acs){
		for (int i = 0; i < adjacentCityIDs.length &&  i < acs.length; i++) {
			adjacentCityIDs[i] = acs[i].cityID;
		}
	}
	
	public static void setAdjacentCities(City ca, Direction dir, City cb){
		int resA = -1;
		int resB = -1;
		switch (dir) {
		case NORTH:
			resA = 0;
			resB = 2;
			break;
		case EAST:
			resA = 1;
			resB = 3;
			break;
		case SOUTH:
			resA = 2;
			resB = 0;
			break;
		case WEST:
			resA = 3;
			resB = 1;
			break;
		}
		
		try {
			City cc = null;
			
			if (ca.adjacentCityIDs[resA] != null) {
				cc = Arch.getCityById(ca.adjacentCityIDs[resA]);
				if (ca.cityID.equals(cc.adjacentCityIDs[resB])) {
					cc.adjacentCityIDs[resB] = null;
				}
			}
			
			if (cb.adjacentCityIDs[resB] != null) {
				cc = Arch.getCityById(cb.adjacentCityIDs[resB]);
				if (cb.cityID.equals(cc.adjacentCityIDs[resA])) {
					cc.adjacentCityIDs[resA] = null;
				}
			}
			
			ca.adjacentCityIDs[resA] = cb.cityID;
			cb.adjacentCityIDs[resB] = ca.cityID;
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
	
	/*------------------------------------------------*/

	public List<Individual> getCitizenInd() {
		return Arch.sto == null ? null : Arch.sto.getIndividualsById(citizens);
	}

	public Zone getParentZone() {
		return Arch.sto == null ? null : Arch.sto.getZoneById(parentZoneID);
	}

	public Language getLanguage() {
		Zone aux = getParentZone();
		return aux == null ? null : aux.getLang();
	}

	public Individual getRandomCitizen() {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizen(this);
	}

	public List<Individual> getRandomCitizens(int n) {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizens(this, n);
	}

	public void changeParentZone(Zone nZone) {
		if(Arch.aie != null) Arch.aie.changeParentZone(this, nZone);
	}
	
	/*------------------------------------------------*/


	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(cityID).append(",");
		res.append(parentZoneID).append(",");
		if (!citizens.isEmpty()) {
			res.append(citizens.get(0));
			for (int i = 1; i < citizens.size(); i++) {
				res.append("@").append(citizens.get(i));
			}
		}
		
		res.append(",");
		for (String string : adjacentCityIDs) {
			if (string != null) {
				res.append(string);
			} else {
				res.append("-");
			}
			res.append("@");
		}
		return res.toString();
	}

	@Override
	public String toString() {
		return "City [cityID=" + cityID + ", citizens=" + citizens + "]";
	}

}
