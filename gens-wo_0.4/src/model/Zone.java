package model;

import java.util.Collection;
import java.util.List;

import model.utils.StringUtils;

public class Zone extends Storable {

	private static long newIDn = (long) (Math.random() * StringUtils.ID_N_25_2);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }
	
	private String zoneID;
	private String parentWorld;
	private Language lang;
	private List<String> cityIDs;
	private float lastUpdated;
	
	public Zone() {
		super(StorableType.ZONE);
		// TODO Auto-generated constructor stub
	}
	
	public static Zone generateFromString(Zone res, String str) {
		if(res == null) res = new Zone();
		//TODO
		return res;
	}
	
	public String getZoneID() {
		return zoneID;
	}
	public void setZoneID(String zoneID) {
		this.zoneID = zoneID;
	}
	
	public String getParentWorld() {
		return parentWorld;
	}
	public void setParentWorld(String parentWorld) {
		this.parentWorld = parentWorld;
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
	@Override
	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}

}
