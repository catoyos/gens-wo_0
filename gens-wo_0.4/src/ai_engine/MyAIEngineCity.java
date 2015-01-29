package ai_engine;

import java.util.LinkedList;
import java.util.List;

import model.Arch;
import model.City;
import model.Individual;

public class MyAIEngineCity {

	public static Individual getRandomCitizen(City city) {
		if (city.getNCitizens() == 0) return null;
		int idx = MyAIEngine.RND.nextInt(city.getNCitizens());
		return Arch.getIndividualById(city.getCitizens().get(idx));
	}

	public static List<Individual> getRandomCitizens(City city, int n) {
		List<Individual> res = new LinkedList<Individual>();
		int nzs = city.getNCitizens();
		if(n <= 0 || nzs == 0){
			return res;
		} else {
			if (n == 1) {
				 res.add(getRandomCitizen(city));
			} else {
				LinkedList<String> itemids = new LinkedList<String>();
				int idx = 0;
				for (int i = 0; i < n; i++) {
					idx = MyAIEngine.RND.nextInt(nzs);					
					itemids.add(city.getCitizens().get(idx));
				}
				res.addAll(Arch.getIndividualsById(itemids));
			}
		}
		return res;
	}
}
