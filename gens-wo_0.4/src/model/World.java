package model;

import java.util.Collection;
import java.util.List;

import model.utils.StringUtils;

public class World extends Storable {

	private static long newIDn = (long) (Math.random() * StringUtils.ID_N_25_2);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }
	
	private String worldID;
	private String parentUni;
	private List<String> zoneIDs;
	private float moment;
	
	public World() {
		super(StorableType.WORLD);
	}
	
	public static World generateFromString(World res, String str) {
		if(res == null) res = new World();
		//TODO
		return res;
	}
	
	public String getWorldID() {
		return worldID;
	}
	public void setWorldID(String worldID) {
		this.worldID = worldID;
	}
	
	public String getParentUni() {
		return parentUni;
	}
	public void setParentUni(String parentUni) {
		this.parentUni = parentUni;
	}
	
	public List<String> getZoneIDs() {
		return zoneIDs;
	}
	public void setZoneIDs(List<String> zoneIDs) {
		this.zoneIDs = zoneIDs;
	}
	public void addZoneId(String sZoneID) {
		zoneIDs.add(sZoneID);
	}
	public void addZone(Zone zZone) {
		zoneIDs.add(zZone.getZoneID());
	}
	public void addAllZoneIDs(Collection<String> sZoneIDs) {
		zoneIDs.addAll(sZoneIDs);
	}
	public void addAllZones(Collection<Zone> zZones) {
		for (Zone zone : zZones) {
			zoneIDs.add(zone.getZoneID());
		}
	}
	public boolean containsZoneID(String sZoneID) {
		return zoneIDs.contains(sZoneID);
	}
	public boolean containsZone(Zone zZone) {
		return zoneIDs.contains(zZone.getZoneID());
	}
	public boolean isEmptyZoneIDs() {
		return zoneIDs.isEmpty();
	}
	public boolean removeZoneID(String sZoneID) {
		return zoneIDs.remove(sZoneID);
	}
	public boolean removeZone(Zone zZone) {
		return zoneIDs.remove(zZone.getZoneID());
	}	
	public int getNZones() {
		return zoneIDs.size();
	}
	
	public float getMoment() {
		return moment;
	}
	public void setMoment(float moment) {
		this.moment = moment;
	}
	public void modifyMoment(float lapse) {
		this.moment += lapse;
	}
	@Override
	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}
}
