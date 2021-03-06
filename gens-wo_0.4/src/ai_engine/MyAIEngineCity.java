package ai_engine;

import java.util.LinkedList;
import java.util.List;

import model.Arch;
import model.City;
import model.Individual;
import model.Zone;

public class MyAIEngineCity {

	public static Individual getRandomCitizen(City city) {
		
		if (city == null || city.getNCitizens() == 0) return null;
		
		int idx = MyAIEngine.RND.nextInt(city.getNCitizens());
		return Arch.getIndividualById(city.getCitizens().get(idx));
	}

	public static List<Individual> getRandomCitizens(City city, int n) {
		List<Individual> res = new LinkedList<Individual>();
		int nzs = city==null ? 0 : city.getNCitizens();
		
		if(n <= 0 || nzs == 0) return res;
		
		else {
			if (n == 1) {
				Individual a = getRandomCitizen(city);
				if (a != null) res.add(a);
			} else {
				LinkedList<String> itemids = new LinkedList<String>();
				List<String> ctzns = city.getCitizens();
				int idx = 0;
				for (int i = 0; i < n; i++) {
					idx = MyAIEngine.RND.nextInt(nzs);					
					itemids.add(ctzns.get(idx));
				}
				res.addAll(Arch.getIndividualsById(itemids));
			}
		}
		return res;
	}

	public static void changeParentZone(City city, Zone nZone) {
		if (nZone != null && !nZone.isID(city.getParentZoneID())) {
			if (city.getParentZoneID() != null) {
				Zone parent = city.getParentZone();
				parent.removeCity(city);
			}
			nZone.addCity(city);
			city.setParentZoneID(nZone.getZoneID());
		}
	}

	public static void procIndividualDeath(City city, Individual individual, float deathDate) {
		if (individual != null && city != null) {
			city.removeCitizen(individual.getIndividualID());
		}
	}
}
