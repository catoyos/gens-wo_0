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

	public static Zone getRandomZone(World world) {
		int nzs = world.getNZones();
		if (nzs > 1) {
			return Arch.getZoneById(world.getZoneIDs().get(MyAIEngine.RND.nextInt(nzs)));
		} else if (nzs == 1) {
			return Arch.getZoneById(world.getZoneIDs().get(0));
		} else {
			return null;
		}
	}

	public static Zone getRandomZonePopWeighted(World world) {
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
				int rzpos = MyAIEngine.RND.nextInt(totinds);
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

	public static Zone getRandomZoneSizeWeighted(World world) {
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
				int rzpos = MyAIEngine.RND.nextInt(totcit);
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

	public static int getNCitizens(World world) {
		int res = 0;
		if (world.getNZones() > 0) {
			List<Zone> zones = world.getZones();
			for (Zone zns : zones) {
				res += zns.getNCitizens();
			}
		}
		return res;
	}

	public static Individual getRandomCitizen(World world) {
		Individual res = null;
		if (world.getNZones() > 0) {
			Zone rz = getRandomZonePopWeighted(world);
			if (rz != null && rz.getNCitizens() > 0) {
				res = rz.getRandomCitizen();
			}
		}
		return res;
	}

	public static List<Individual> getRandomCitizens(World world, int n) {
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

	public static void updateZones(World world) {
		// TODO updateZones

	}

	public static void updateIndividuals(World world) {
		List<Zone> zns = world.getZones();
		float moment = world.getMoment();
		for (Zone zone : zns) {
			zone.updateIndividuals(moment);
		}
	}
}
