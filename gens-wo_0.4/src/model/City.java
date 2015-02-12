package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class City extends Storable {

	private static final String FILESTRING_SEP = ",".intern();
	private static final String LIST_SEP = "@".intern();
	private static final String EMPTY_ID = "".intern();
	private static final String NULL_VALUE = "-".intern();

	public static enum Direction { NORTH, EAST, SOUTH, WEST };

	protected static long newIDn = Arch.RND.nextInt(StringUtils.ID_N_25_3);

	private String cityID;
	private String parentZoneID;
	private List<String> citizens;
	private String[] adjacentCityIDs;
	
	public City() {
		super(StorableType.CITY);
		this.cityID = EMPTY_ID;
		this.citizens = new LinkedList<String>();
	}
	
	public static void generateNewCity(City res, String worldID, String parentZoneID) {
		res.cityID = (worldID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_3), 3)).intern();
		res.parentZoneID = parentZoneID;
		
		if (res.citizens == null) {
			res.citizens = new LinkedList<String>();
		} else {
			res.citizens.clear();
		}
		res.adjacentCityIDs = new String[4];
	}
	
	public static void generateFromString(City res, String string) {
		String[] data = string.split(FILESTRING_SEP);
		res.cityID = data[0].intern();
		res.parentZoneID = data[1].intern();

		if (data.length > 2 && !"".equals(data[2])) {
			res.citizens = StringUtils.compactStringToList(data[2], LIST_SEP);
		} else if (res.citizens == null) {
			res.citizens = new LinkedList<String>();
		} else {
			res.citizens.clear();
		}
		
		res.adjacentCityIDs = new String[4];
		if (data.length > 3) {
			List<String> adjcts = StringUtils.compactStringToList(data[3], LIST_SEP, NULL_VALUE);
			for (int i = 0; i < res.adjacentCityIDs.length; i++) {
				res.adjacentCityIDs[i] = adjcts.get(i);
			}
		}
	}

	public void clear() {
		super.modified = false;
		this.cityID = EMPTY_ID;
		this.parentZoneID = null;
		if(citizens == null) this.citizens = new LinkedList<String>();
		else this.citizens.clear();
		this.adjacentCityIDs = null;
	}
	
	public String getCityID() {
		return cityID;
	}

//	public void setCityID(String cityID) {
//		super.modified = true;
//		this.cityID = cityID;
//	}

	public boolean isID(String oID) {
		return oID != null && this.cityID.equals(oID) && !this.cityID.equals(EMPTY_ID);
	}
	
	public String getParentZoneID() {
		return parentZoneID;
	}

	public void setParentZoneID(String parentZoneID) {
		super.modified = true;
		this.parentZoneID = parentZoneID;
	}

	public List<String> getCitizens() {
		return Collections.unmodifiableList(citizens);
	}

	public void setCitizens(List<String> individuals) {
		super.modified = true;
		this.citizens = individuals;
	}

	public void addCitizen(String sIndividualID) {
		super.modified = true;
		citizens.add(sIndividualID);
	}

	public void addCitizen(Individual zIndividual) {
		super.modified = true;
		if (zIndividual!=null) citizens.add(zIndividual.getIndividualID());
	}

	public boolean containsCitizen(String sIndividualID) {
		return citizens.contains(sIndividualID);
	}

	public boolean containsCitizen(Individual zIndividual) {
		if (zIndividual == null) return false;
		return citizens.contains(zIndividual.getIndividualID());
	}

	public boolean isEmptyCitizens() {
		return citizens.isEmpty();
	}

	public boolean removeCitizen(String sIndividualID) {
		super.modified = true;
		return citizens.remove(sIndividualID);
	}

	public boolean removeCitizen(Individual zIndividual) {
		if (zIndividual==null) return false;
		super.modified = true;
		return citizens.remove(zIndividual.getIndividualID());
	}

	public int getNCitizens() {
		return citizens.size();
	}

	public String[] getAdjacentCityIDs() {//TODO no permitir acceso a array
		return adjacentCityIDs;
	}

	protected void setAdjacentCityIDs(String[] adjacentCityIDs) {
		super.modified = true;
		this.adjacentCityIDs = adjacentCityIDs;
	}
	
	protected void setAdjacentCities(City[] acs){
		super.modified = true;
		for (int i = 0; i < adjacentCityIDs.length &&  i < acs.length; i++) {
			if (acs[i]!=null) adjacentCityIDs[i] = acs[i].cityID;
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
		
		if (ca != null && cb != null) try {
			
			City cc = null;
			
			if (ca.adjacentCityIDs[resA] != null) {
				cc = Arch.getCityById(ca.adjacentCityIDs[resA]);
				if (ca.isID(cc.adjacentCityIDs[resB])) {
					cc.adjacentCityIDs[resB] = null;
					cc.modified = true;
				}
			}
			
			if (cb.adjacentCityIDs[resB] != null) {
				cc = Arch.getCityById(cb.adjacentCityIDs[resB]);
				if (cb.isID(cc.adjacentCityIDs[resA])) {
					cc.adjacentCityIDs[resA] = null;
					cc.modified = true;
				}
			}
			
			ca.adjacentCityIDs[resA] = cb.cityID;
			cb.adjacentCityIDs[resB] = ca.cityID;
			ca.modified = true;
			cb.modified = true;
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

//	public Language getLanguage() {
//		Zone aux = getParentZone();
//		return aux == null ? null : aux.getLang();
//	}

	public Individual getRandomCitizen() {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizen(this);
	}

	public List<Individual> getRandomCitizens(int n) {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizens(this, n);
	}

	public void changeParentZone(Zone nZone) {
		if(Arch.aie != null) Arch.aie.changeParentZone(this, nZone);
	}
	
	public void procIndividualDeath(Individual individual, float deathDate) {
		if(Arch.aie != null) Arch.aie.procIndividualDeath(this, individual, deathDate);
	}
	
	/*------------------------------------------------*/


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityID == null) ? 0 : cityID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityID == null) {
			if (other.cityID != null)
				return false;
		} else if (!cityID.equals(other.cityID))
			return false;
		return true;
	}

	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(cityID).append(FILESTRING_SEP);
		res.append(parentZoneID).append(FILESTRING_SEP);
		res.append(StringUtils.listToCompactString(citizens, LIST_SEP)).append(FILESTRING_SEP);
		res.append(StringUtils.listToCompactString(Arrays.asList(adjacentCityIDs), LIST_SEP, NULL_VALUE));
		return res.toString();
	}
	
	@Override
	public String toString() {
		return "City [cityID=" + cityID + ", citizens=" + citizens + "]";
	}

}
