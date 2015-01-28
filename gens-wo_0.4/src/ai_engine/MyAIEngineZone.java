package ai_engine;

import java.util.List;

import model.City;
import model.Individual;
import model.Zone;

public class MyAIEngineZone {


	public static City getRandomCity(Zone zone) {
		int nzs = zone.getNCities();
		if (nzs > 1) {
			return Arch.getCityById(world.getCityIDs().get((int) (Math.random() * nzs)));
		} else if (nzs == 1) {
			return Arch.getCityById(world.getCityIDs().get(0));
		} else {
			return null;
		}
	}

	public static City getRandomCityPopWeighted(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getNCitizens(Zone zone) {
		int res = 0;
		if (zone.getNCities() > 0) {
			List<City> cities = world.getCities();
			for (City cns : cities) {
				res += cns.getNCitizens();
			}
		}
		return res;
	}

	public static Individual getRandomCitizen(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Individual> getRandomCitizens(Zone zone, int n) {
		// TODO Auto-generated method stub
		return null;
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
			if(zone.getID().equals(auzn) && !res.contains(auzn)){
				res.add(auzn);
				if(cities.size() > 1){
					cities.removeAll(Arch.getZoneByID(auzn).getZoneIDs());
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
		if(zone==null||adj==null)
			return false;
			
		if(zone.getZoneID().equals(adj.getParentZoneID()))
			return false;
			
		if(zone.getNCities()==0)
			return false;
			
		adct = adj.getAdjacentCityIDs();
		for(int i = 0; i < adct.length; i++){
			if(zone.containsCityID(adct[i])) return true;
		}
		
		return false;
	}
	
	public static boolean isZoneAdjacent(Zone zone, Zone adj){
		// TODO Auto-generated method stub
		return false;
	}

	public static void updateIndividuals(Zone zone, float moment) {
		// TODO Auto-generated method stub
		
	}
}
