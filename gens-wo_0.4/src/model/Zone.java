package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class Zone extends Storable {

	private static long newIDn = (long) (Math.random() * StringUtils.ID_N_25_2);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }
	
	private String zoneID;
	private String parentWorldID;
	private Language lang;
	private List<String> cityIDs;
	private float lastUpdated;
	
	public Zone() {
		super(StorableType.ZONE);
		this.cityIDs = new LinkedList<String>();
		this.lang = new Language();
	}
	
	public static Zone generateNewZone(Zone res, String worldID) {
		res.zoneID = worldID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_2), 2);
		res.parentWorldID = worldID;
		res.lang = new Language();
		if (res.cityIDs == null) {
			res.cityIDs = new LinkedList<String>();
		} else {
			res.cityIDs.clear();
		}
		res.lastUpdated = 0;
		return res;
	}
	
	public static Zone generateFromString(Zone res, String string) {
		String[] data = string.split(",");
		res.zoneID = data[0];
		res.parentWorldID = data[1];
		try {
			res.lastUpdated = Float.parseFloat(data[2]);
		} catch (Exception e) {
			e.printStackTrace();
			res.lastUpdated = 0;
		}
		
		if (res.lang == null) {
			res.lang = new Language();
		}
		res.lang = Language.generateFromString(res.lang, "");//TODO
		
		if (data.length > 2) {
			res.cityIDs = Arrays.asList(data[3].split("@"));
		} else if (res.cityIDs == null) {
			res.cityIDs = new LinkedList<String>();
		} else {
			res.cityIDs.clear();
		}
		
		return res;
	}
	
	public String getZoneID() {
		return zoneID;
	}
	public void setZoneID(String zoneID) {
		this.zoneID = zoneID;
	}
	
	public String getParentWorldID() {
		return parentWorldID;
	}
	public void setParentWorldID(String parentWorldID) {
		this.parentWorldID = parentWorldID;
	}
	
	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}
	
	public List<String> getCityIDs() {
		return cityIDs;
	}
	public void setCityIDs(List<String> cityIDs) {
		this.cityIDs = cityIDs;
	}
	public void addCityId(String sCityID) {
		cityIDs.add(sCityID);
	}
	public void addCity(City zCity) {
		cityIDs.add(zCity.getCityID());
	}
	public void addAllCityIDs(Collection<String> sCityIDs) {
		cityIDs.addAll(sCityIDs);
	}
	public void addAllCities(Collection<City> zCities) {
		for (City city : zCities) {
			cityIDs.add(city.getCityID());
		}
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
		return cityIDs.remove(sCityID);
	}
	public boolean removeCity(City zCity) {
		return cityIDs.remove(zCity.getCityID());
	}	
	public int getNCities() {
		return cityIDs.size();
	}
	
	public float getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(float lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public void modifyUpdated(float lapse) {
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
	 * @param moment
	 */
	public void updateCitizens(float moment) {
		if (Arch.aie != null) {
			Arch.aie.updateCitizens(this, moment);
		}
	}

	/*------------------------------------------------*/
	
	@Override
	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(zoneID).append(",");
		res.append(parentWorldID).append(",");
		res.append(lastUpdated);
		if (!cityIDs.isEmpty()) {
			res.append(",").append(cityIDs.get(0));
			for (int i = 1; i < cityIDs.size(); i++) {
				res.append("@").append(cityIDs.get(i));
			}
		} else {
			res.append(",");
		}
		return res.toString();
	}

	@Override
	public String toString() {
		return "Zone [zoneID=" + zoneID + "]";
	}
}
