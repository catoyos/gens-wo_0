package ai_engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Arch;
import model.Individual;
import model.World;
import model.Zone;

public class MyAIEngineWorld {

//	public static List<Zone> getZones(World world) {
//		return Arch.getZonesById(world.getZoneIDs());
//	}

	public Zone getRandomZone(World world) {
		int nzs = world.getNZones();
		if (nzs > 1) {
			return Arch.getZoneById(world.getZoneIDs().get((int) (Math.random() * nzs)));
		} else if (nzs == 1) {
			return Arch.getZoneById(world.getZoneIDs().get(0));
		} else {
			return null;
		}
	}

	public Zone getRandomZonePopWeighted(World world) {
		Zone res = null;
		int nzs = world.getNZones();
		if (nzs > 1) {
			List<Zone> zones = world.getZones();
			int[] zz = new int[nzs];
			int totinds = 0;
			for (int i = 0; i < zz.length; i++) {
				zz[i] = zones.get(i).getNCitizens();
				totinds += zz[i];
			}
			if (totinds > 0) {
				int rzpos = (int) (totinds * Math.random());
				for (int i = 0; i < zz.length && res == null; i++) {
					rzpos -= zz[i];
					if (rzpos <= 0) {
						res = zones.get(i);
					}
				}
			} else {
				res = getRandomZone(world);
			}
		} else if (nzs == 1) {
			res = Arch.getZoneById(world.getZoneIDs().get(0));
		}

		return res;
	}

	public Zone getRandomZoneSizeWeighted(World world) {
		Zone res = null;
		int nzs = world.getNZones();
		if (nzs > 1) {
			List<Zone> zones = world.getZones();
			int[] zz = new int[nzs];
			int totcit = 0;
			for (int i = 0; i < zz.length; i++) {
				zz[i] = zones.get(i).getNCities();
				totcit += zz[i];
			}
			if (totcit > 0) {
				int rzpos = (int) (totcit * Math.random());
				for (int i = 0; i < zz.length && res == null; i++) {
					rzpos -= zz[i];
					if (rzpos <= 0) {
						res = zones.get(i);
					}
				}
			} else {
				res = getRandomZone(world);
			}
		} else if (nzs == 1) {
			res = Arch.getZoneById(world.getZoneIDs().get(0));
		}

		return res;
	}

	public int getNCitizens(World world) {
		int res = 0;
		if (world.getNZones() > 0) {
			List<Zone> zones = world.getZones();
			for (Zone zns : zones) {
				res += zns.getNCitizens();
			}
		}
		return res;
	}

	public Individual getRandomCitizen(World world) {
		Individual res = null;
		if (world.getNZones() > 0) {
			Zone rz = getRandomZonePopWeighted(world);
			if (rz != null && rz.getNCitizens() > 0) {
				res = rz.getRandomCitizen();
			}
		}
		return res;
	}

	public List<Individual> getRandomCitizens(World world, int n) {
		List<Individual> res = new LinkedList<Individual>();

		if (n > 1) {
			List<Zone> zones = world.getZones();
			int[] zz = new int[zones.size()];
			float totinds = 0;
			for (int i = 0; i < zz.length; i++) {
				zz[i] = zones.get(i).getNCitizens();
				totinds += zz[i];
			}
			if (totinds > 0) {
				for (int i = 0; i < zz.length; i++) {
					if (zz[i] > 0) {
						int idx = (int) Math.ceil(n * zz[i] / totinds);
						res.addAll(zones.get(i).getRandomCitizens(idx));
					}
				}
				if (res.size() > 3) {
					Collections.shuffle(res);
				}
			}
		} else if (n == 1) {
			res.add(getRandomCitizen(world));
		}

		if (res.size() > n) {
			return res.subList(0, n + 1);
		} else {
			return res;
		}
	}

	public void updateZones(World world) {
		// TODO Auto-generated method stub

	}

	public void updateIndividuals(World world) {
		// TODO Auto-generated method stub

	}
}