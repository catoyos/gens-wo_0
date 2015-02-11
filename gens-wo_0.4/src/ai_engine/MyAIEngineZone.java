package ai_engine;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Arch;
import model.City;
import model.Individual;
import model.Zone;

public class MyAIEngineZone {

	public static City getRandomCity(Zone zone) {
		int nzs = zone.getNCities();
		if (nzs > 1) {
			return Arch.getCityById(zone.getCityIDs().get(MyAIEngine.RND.nextInt(nzs)));
		} else if (nzs == 1) {
			return Arch.getCityById(zone.getCityIDs().get(0));
		} else {
			return null;
		}
	}

	public static City getRandomCityPopWeighted(Zone zone) {
		City res = null;
		int nzs = zone.getNCities();
		if (nzs > 1) {
			List<City> cities = zone.getCities();
			int[] zz = new int[nzs];
			int totinds = 0;
			for (int i = 0; i < zz.length; i++) {
				zz[i] = cities.get(i).getNCitizens();
				totinds += zz[i];
			}
			if (totinds > 0) {
				int rzpos = MyAIEngine.RND.nextInt(totinds);
				for (int i = 0; i < zz.length && res == null; i++) {
					rzpos -= zz[i];
					if (rzpos <= 0) {
						res = cities.get(i);
					}
				}
			} else {
				res = getRandomCity(zone);
			}

		} else if (nzs == 1) {
			res = Arch.getCityById(zone.getCityIDs().get(0));
		}

		return res;
	}

	public static int getNCitizens(Zone zone) {
		int res = 0;
		if (zone.getNCities() > 0) {
			List<City> cities = zone.getCities();
			for (City cns : cities) {
				res += cns.getNCitizens();
			}
		}
		return res;
	}

	public static Individual getRandomCitizen(Zone zone) {		
		Individual res = null;
		if (zone.getNCities() > 0) {
			City rz = getRandomCityPopWeighted(zone);
			if (rz != null && rz.getNCitizens() > 0) {
				res = rz.getRandomCitizen();
			}
		}
		return res;
	}

	public static List<Individual> getRandomCitizens(Zone zone, int n) {
		List<Individual> res = new LinkedList<Individual>();
		if (n > 1) {
			List<City> cities = zone.getCities();
			int[] zz = new int[cities.size()];
			float totinds = 0;
			for (int i = 0; i < zz.length; i++) {
				zz[i] = cities.get(i).getNCitizens();
				totinds += zz[i];
			}
			if (totinds > 0) {
				for (int i = 0; i < zz.length; i++) {
					if (zz[i] > 0) {
						int idx = (int) Math.ceil(n * zz[i] / totinds);
						res.addAll(cities.get(i).getRandomCitizens(idx));
					}
				}
				if (res.size() > 3) {
					Collections.shuffle(res);
				}
			}
		} else if (n == 1) {
			Individual a = getRandomCitizen(zone);
			if (a != null) res.add(a);
		}

		if (res.size() > n) {
			return res.subList(0, n + 1);
		} else {
			return res;
		}
	}
	
	public static List<String> getAdjacentCityIDs(Zone zone){
		List<String> cities = zone.getCityIDs();
		List<String> res = new LinkedList<String>();
		City aux = null;
		String[] adct = null;
		for(String cid : cities){
			aux = Arch.getCityById(cid);
			adct = aux.getAdjacentCityIDs();
			for(int i = 0; i < adct.length; i++){
				if(!cities.contains(adct[i]) && !res.contains(adct[i])){
					res.add(adct[i]);
				}
			}
		}
		return res;
	}
	
	public static List<City> getAdjacentCities(Zone zone){
		return Arch.getCitiesById(getAdjacentCityIDs(zone));
	}
	
	public static List<String> getAdjacentZoneIDs(Zone zone){
		List<String> cities = getAdjacentCityIDs(zone);
		List<String> res = new LinkedList<String>();
		City aux = null;
		String auzn;
		while(!cities.isEmpty()){
			String cid = cities.get(0);
			aux = Arch.getCityById(cid);
			auzn = aux.getParentZoneID();
			if(zone.isID(auzn) && !res.contains(auzn)){
				res.add(auzn);
				if(cities.size() > 1){
					cities.removeAll(Arch.getZoneById(auzn).getCityIDs());
				} else {
					cities.remove(0);
				}
			} else {
				cities.remove(0);
			}
		}
		return res;
	}
	
	public static List<Zone> getAdjacentZones(Zone zone){
		return Arch.getZonesById(getAdjacentZoneIDs(zone));
	}
	
	public static boolean isCityAdjacent(Zone zone, City adj){
		if(zone==null || adj==null)
			return false;
			
		if(zone.equals(adj))
			return false;
			
		if(zone.getNCities() == 0)
			return false;
			
		String[] adct = adj.getAdjacentCityIDs();
		for(int i = 0; i < adct.length; i++){
			if(zone.containsCityID(adct[i]))
				return true;
		}
		
		return false;
	}
	
	public static boolean isZoneAdjacent(Zone zone, Zone adj){
		if (zone==null || adj==null)
			return false;
			
		if (zone.equals(adj))
			return false;
			
		if (zone.getNCities() == 0 || adj.getNCities() == 0)
			return false;
		
		Zone aux = null;
		List<String> cities = null;
		if (zone.getNCities() < adj.getNCities()) {
			aux = adj;
			cities = getAdjacentCityIDs(zone);
		} else {
			aux = zone;
			cities = getAdjacentCityIDs(adj);
		}
		for (String string : cities) {
			if(aux.containsCityID(string))
				return true;
		}
				
		return false;
	}

	public static void updateIndividuals(Zone zone, float moment) {
		float zlu = zone.getLastUpdated();
		float lapse = (moment - zlu);
		int n = (int) (zone.getNCitizens() * lapse * 0.5);
		if (lapse < 2) {
			n++;
		}
		
		if (lapse > 0) {
			float indlp = lapse / n;
			List<Individual> ins = getRandomCitizens(zone, n);
			for (Individual individual : ins) {
				zlu += indlp;
				individual.update(zlu);
			}
			zone.setLastUpdated(zlu);
			zone.getZoneID().equals("");
		}
	}
}
