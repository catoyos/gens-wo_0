package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Individual.Gender;
import model.utils.StringUtils;

public class Zone extends Storable {

	private static final String FILESTRING_SEP = ",".intern();
	private static final String LIST_SEP = "@".intern();
	private static final String EMPTY_ID = "".intern();
	
	protected static long newIDn = Arch.RND.nextInt(StringUtils.ID_N_25_2);
	
	private String zoneID;
	private String parentWorldID;
	private Language lang;
	private List<String> cityIDs;
	private float lastUpdated;
	
	public Zone() {
		super(StorableType.ZONE);
		this.zoneID = EMPTY_ID;
		this.cityIDs = new LinkedList<String>();
		this.lang = new Language();
	}
	
	public static void generateNewZone(Zone res, String worldID) {
		res.zoneID = (worldID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_2), 2)).intern();
		res.parentWorldID = worldID;
		res.lang = Arch.generateNewLanguage(res.zoneID);
		if (res.cityIDs == null) {
			res.cityIDs = new LinkedList<String>();
		} else {
			res.cityIDs.clear();
		}
		res.lastUpdated = 0;
	}
	
	public static void generateFromString(Zone res, String string) {
		String[] data = string.split(FILESTRING_SEP);
		res.zoneID = data[0].intern();
		res.parentWorldID = data[1].intern();
		try {
			res.lastUpdated = Float.parseFloat(data[2]);
		} catch (Exception e) {
			e.printStackTrace();
			res.lastUpdated = 0;
		}
		
		res.lang = null;
		
		if (data.length > 3) {
			res.cityIDs = StringUtils.compactStringToList(data[3], LIST_SEP);
		} else if (res.cityIDs == null) {
			res.cityIDs = new LinkedList<String>();
		} else {
			res.cityIDs.clear();
		}

	}

	public void clear() {		
		super.modified = false;
		this.zoneID = EMPTY_ID;
		this.parentWorldID = null;
		if(lang == null) this.lang = new Language();
		else this.lang.clear();
		
		if(cityIDs == null) this.cityIDs = new LinkedList<String>();
		else this.cityIDs.clear();
		
		this.lastUpdated = 0;
	}
	
	public String getZoneID() {
		return zoneID;
	}
//	public void setZoneID(String zoneID) {
//		super.modified = true;
//		this.zoneID = zoneID;
//	}

	public boolean isID(String oID) {
		return oID != null && this.zoneID.equals(oID) && !this.zoneID.equals(EMPTY_ID);
	}	
	
	public String getParentWorldID() {
		return parentWorldID;
	}
	public void setParentWorldID(String parentWorldID) {
		super.modified = true;
		this.parentWorldID = parentWorldID;
	}
	
//	public Language getLang() {
//		return lang;
//	}
//	public void setLang(Language lang) {
//		this.lang = lang;
//	}
	public String getRandomName(Gender gender) {
		if (lang == null) lang = Arch.getLanguageById(zoneID);
		return lang == null ? null : lang.getRandomName(gender);
	}
	public String getRandomSurname() {
		if (lang == null) lang = Arch.getLanguageById(zoneID);
		return lang == null ? null : lang.getRandomSurname();
	}
	
	public List<String> getCityIDs() {
		return Collections.unmodifiableList(cityIDs);
	}
	public void setCityIDs(List<String> cityIDs) {
		super.modified = true;
		this.cityIDs = cityIDs;
	}
	public void addCityId(String sCityID) {
		super.modified = true;
		cityIDs.add(sCityID);
	}
	public void addCity(City zCity) {
		super.modified = true;
		cityIDs.add(zCity.getCityID());
	}

	public boolean containsCityID(String sCityID) {
		return cityIDs.contains(sCityID);
	}
	public boolean containsCity(City zCity) {
		return cityIDs.contains(zCity.getCityID());
	}
	public boolean isEmptyCityIDs() {
		return cityIDs.isEmpty();
	}
	public boolean removeCityID(String sCityID) {
		super.modified = true;
		return cityIDs.remove(sCityID);
	}
	public boolean removeCity(City zCity) {
		super.modified = true;
		return cityIDs.remove(zCity.getCityID());
	}	
	public int getNCities() {
		return cityIDs.size();
	}
	
	public float getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(float lastUpdated) {
		super.modified = true;
		this.lastUpdated = lastUpdated;
	}
	public void modifyUpdated(float lapse) {
		super.modified = true;
		this.lastUpdated += lapse;
	}
	
	/*------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	public List<City> getCities() {
		return Arch.sto == null ? null : Arch.sto.getCitiesById(cityIDs);
	}
	
	/**
	 * 
	 * @return
	 */
	public World getParentWorld() {
		return Arch.sto == null ? null : Arch.sto.getWorldById(parentWorldID);
	}
	
	/**
	 * 
	 * @return
	 */
	public City getRandomCity() {
		return Arch.aie == null ? null : Arch.aie.getRandomCity(this);
	}

	/**
	 * 
	 * @return
	 */
	public City getRandomCityPopWeighted() {
		return Arch.aie == null ? null : Arch.aie.getRandomCityPopWeighted(this);
	}

	/**
	 * 
	 * @return
	 */
	public int getNCitizens() {
		return Arch.aie == null ? -1 : Arch.aie.getNCitizens(this);
	}

	/**
	 * 
	 * @return
	 */
	public Individual getRandomCitizen() {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizen(this);
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public List<Individual> getRandomCitizens(int n) {
		return Arch.aie == null ? null : Arch.aie.getRandomCitizens(this, n);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<City> getAdjacentCities() {
		return Arch.aie == null ? null : Arch.aie.getAdjacentCities(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Zone> getAdjacentZones() {
		return Arch.aie == null ? null : Arch.aie.getAdjacentZones(this);
	}

	/**
	 * 
	 * @param zone
	 * @return
	 */
	public boolean isZoneAdjacent(Zone adj) {
		if(adj == null) return false;
		return Arch.aie == null ? false : Arch.aie.isZoneAdjacent(this, adj);
	}

	/**
	 * 
	 * @param zone
	 * @return
	 */
	public boolean isCityAdjacent(City adj) {
		if(adj == null) return false;
		return Arch.aie == null ? false : Arch.aie.isCityAdjacent(this, adj);
	}


	/**
	 * 
	 * @param moment
	 */
	public void updateIndividuals(float moment) {
		if (Arch.aie != null) {
			Arch.aie.updateIndividuals(this, moment);
		}
	}

	/*------------------------------------------------*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zoneID == null) ? 0 : zoneID.hashCode());
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
		Zone other = (Zone) obj;
		if (zoneID == null) {
			if (other.zoneID != null)
				return false;
		} else if (!zoneID.equals(other.zoneID))
			return false;
		return true;
	}
	
	@Override
	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(zoneID).append(FILESTRING_SEP);
		res.append(parentWorldID).append(FILESTRING_SEP);
		res.append(lastUpdated).append(FILESTRING_SEP);
		res.append(StringUtils.listToCompactString(cityIDs, LIST_SEP));
		return res.toString();
	}
}
