package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class World extends Storable {

	private static long newIDn = Arch.RND.nextInt(StringUtils.ID_N_25_2);
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }
	
	private String worldID;
	private String parentUni;
	private List<String> zoneIDs;
	private float moment;
	
	public World() {
		super(StorableType.WORLD);
		this.zoneIDs = new LinkedList<String>();
	}
	
	public static void generateNewWorld(World res, String uniID) {
		res.worldID = uniID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_2), 2);
		res.parentUni = uniID;
		if (res.zoneIDs == null) {
			res.zoneIDs = new LinkedList<String>();
		} else {
			res.zoneIDs.clear();
		}
		res.moment = 0;
	}
	
	public static void generateFromString(World res, String str) {
		String[] data = str.split(",");
		res.worldID = data[0];
		res.parentUni = data[1];
		try {
			res.moment = Float.parseFloat(data[2]);
		} catch (Exception e) {
			e.printStackTrace();
			res.moment = 0;
		}
		
		if (data.length > 2) {
			res.zoneIDs = new LinkedList<String>(Arrays.asList(data[3].split("@")));
		} else if (res.zoneIDs == null) {
			res.zoneIDs = new LinkedList<String>();
		} else {
			res.zoneIDs.clear();
		}
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

	
	/*------------------------------------------------*/
	
	/**
	 * 
	 * @return
	 */
	public List<Zone> getZones() {
		return Arch.sto == null ? null : Arch.sto.getZonesById(zoneIDs);
	}

	/**
	 * 
	 * @return
	 */
	public Zone getRandomZone() {
		return Arch.aie == null ? null : Arch.aie.getRandomZone(this);
	}

	/**
	 * 
	 * @return
	 */
	public Zone getRandomZonePopWeighted() {
		return Arch.aie == null ? null : Arch.aie.getRandomZonePopWeighted(this);
	}

	/**
	 * 
	 * @return
	 */
	public Zone getRandomZoneSizeWeighted() {
		return Arch.aie == null ? null : Arch.aie.getRandomZoneSizeWeighted(this);
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
	 */
	public void updateZones() {
		if (Arch.aie != null) {
			Arch.aie.updateZones(this);
		}
	}

	/**
	 * 
	 */
	public void updateIndividuals() {
		if (Arch.aie != null) {
			Arch.aie.updateIndividuals(this);
		}
	}
	
	/*------------------------------------------------*/
	
	@Override
	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(worldID).append(",");
		res.append(parentUni).append(",");
		res.append(moment);
		if (!zoneIDs.isEmpty()) {
			res.append(",").append(zoneIDs.get(0));
			for (int i = 1; i < zoneIDs.size(); i++) {
				res.append("@").append(zoneIDs.get(i));
			}
		} else {
			res.append(",");
		}
		return res.toString();
	}

	@Override
	public String toString() {
		return "World [worldID=" + worldID + ", moment=" + moment + ", num_zonas=" + zoneIDs.size() + ", zonas=" + zoneIDs.toString() + "]";
	}
}
