package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class World extends Storable {

	private static final String FILESTRING_SEP = ",".intern();
	private static final String LIST_SEP = "@".intern();
	private static final String EMPTY_ID = "".intern();

	protected static long newIDn = Arch.RND.nextInt(StringUtils.ID_N_25_2);
	
	private String worldID;
	private String parentUni;
	private List<String> zoneIDs;
	private float moment;
	
	public World() {
		super(StorableType.WORLD);
		this.zoneIDs = new LinkedList<String>();
		this.worldID = EMPTY_ID;
	}
	
	public static void generateNewWorld(World res, String uniID) {
		res.worldID = (uniID + StringUtils.generateId((int)(newIDn++ % StringUtils.ID_N_25_2), 2)).intern();
		res.parentUni = uniID;
		if (res.zoneIDs == null) {
			res.zoneIDs = new LinkedList<String>();
		} else {
			res.zoneIDs.clear();
		}
		res.moment = 0;
	}
	
	public static void generateFromString(World res, String str) {
		String[] data = str.split(FILESTRING_SEP);
		res.worldID = data[0].intern();
		res.parentUni = data[1].intern();
		try {
			res.moment = Float.parseFloat(data[2]);
		} catch (Exception e) {
			e.printStackTrace();
			res.moment = 0;
		}
		
		if (data.length > 3) {
			res.zoneIDs = StringUtils.compactStringToList(data[3], LIST_SEP);
		} else if (res.zoneIDs == null) {
			res.zoneIDs = new LinkedList<String>();
		} else {
			res.zoneIDs.clear();
		}
	}

	public void clear() {		
		super.modified = false;
		this.worldID = EMPTY_ID;
		this.parentUni = null;
		if(zoneIDs == null) this.zoneIDs = new LinkedList<String>();
		else this.zoneIDs.clear();
		
		this.moment = 0;
	}
	
	public String getWorldID() {
		return worldID;
	}
//	public void setWorldID(String worldID) {
//		super.modified = true;
//		this.worldID = worldID;
//	}

	public boolean isID(String oID) {
		return oID != null && this.worldID.equals(oID) && !this.worldID.equals(EMPTY_ID);
	}
	
	public String getParentUni() {
		return parentUni;
	}
	public void setParentUni(String parentUni) {
		super.modified = true;
		this.parentUni = parentUni;
	}
	
	public List<String> getZoneIDs() {
		return Collections.unmodifiableList(zoneIDs);
	}
	public void setZoneIDs(List<String> zoneIDs) {
		super.modified = true;
		this.zoneIDs = zoneIDs;
	}
	public void addZoneId(String sZoneID) {
		super.modified = true;
		zoneIDs.add(sZoneID);
	}
	public void addZone(Zone zZone) {
		super.modified = true;
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
		super.modified = true;
		return zoneIDs.remove(sZoneID);
	}
	public boolean removeZone(Zone zZone) {
		super.modified = true;
		return zoneIDs.remove(zZone.getZoneID());
	}	
	public int getNZones() {
		return zoneIDs.size();
	}
	
	public float getMoment() {
		return moment;
	}
	public void setMoment(float moment) {
		super.modified = true;
		this.moment = moment;
	}
	public void modifyMoment(float lapse) {
		super.modified = true;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((worldID == null) ? 0 : worldID.hashCode());
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
		World other = (World) obj;
		if (worldID == null) {
			if (other.worldID != null)
				return false;
		} else if (!worldID.equals(other.worldID))
			return false;
		return true;
	}
	
	@Override
	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(worldID).append(FILESTRING_SEP);
		res.append(parentUni).append(FILESTRING_SEP);
		res.append(moment).append(FILESTRING_SEP);

		res.append(StringUtils.listToCompactString(zoneIDs, LIST_SEP));
		
		return res.toString();
	}
	@Override
	public String toString() {
		return "World [worldID=" + worldID + ", moment=" + moment + ", num_zonas=" + zoneIDs.size() + ", zonas=" + zoneIDs.toString() + "]";
	}
}
