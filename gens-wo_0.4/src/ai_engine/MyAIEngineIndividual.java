package ai_engine;

import java.util.LinkedList;
import java.util.List;

import model.Arch;
import model.City;
import model.Individual;
import model.Individual.Gender;
import model.Zone;

public class MyAIEngineIndividual {


	public static String getCurrentCityFromParents(Individual father,
			Individual mother) {
		if (father.isAlive() == mother.isAlive()) {
			if (father.getRep() > (2 * mother.getRep())) {
				return father.getCurrentCityID();
			} else {
				return mother.getCurrentCityID();
			}
		} else {
			if (father.isAlive()) {
				return father.getCurrentCityID();
			} else {
				return mother.getCurrentCityID();
			}
		}
	}

	public static String[] getNameSurnamesFromParents(Gender gender, Individual father, Individual mother) {
		String[] res = new String[3];
		Zone lang = null;
		float difrep = father.getRep() - mother.getRep();
		if ((difrep > 0) || ((difrep == 0) && (father.getBirthDate() < mother.getBirthDate()))) {
			lang = father.getOriginalZone();
			res[1] = father.getSurnameA();
			res[2] = mother.getSurnameA();
		} else {
			lang = mother.getOriginalZone();
			res[1] = mother.getSurnameA();
			res[2] = father.getSurnameA();
		}
		if (lang != null) {
			res[0] = lang.getRandomName(gender);
		}
		
		return res;
	}

	public static float getRepInheritedFromParents(Individual father, Individual mother) {
		return Math.round(25 * (father.getRep() + mother.getRep())) * 0.01f;
	}

	public static void pairIndividuals(Individual indA, Individual indB) {
		if (indA.hasPartner()) {
			if (!indA.getPartnerID().equals(indB.getIndividualID())) {
				divorce(indA, indA.getPartner());
			}
		}
		
		if (indB.hasPartner()) {
			if (!indB.getPartnerID().equals(indA.getIndividualID())) {
				divorce(indB.getPartner(), indB);
			}
		}

		indA.setPartnerID(indB.getIndividualID());
		indB.setPartnerID(indA.getIndividualID());
		//TODO cambiar residencia??
	}

	public static void divorce(Individual indA, Individual indB) {
		String pa = indA.getPartnerID();
		String pb = indB.getPartnerID();
		
		if(pa != null && pa.equals(indB.getIndividualID())) indA.setPartnerID(null);
		if(pb != null && pb.equals(indA.getIndividualID())) indB.setPartnerID(null);
	}

	public static void propagateReputation(Individual individual, float val) {
		if (val >= 0.4f && !individual.isEmptyChildren()) {
			List<Individual> children = individual.getChildren();
			for (Individual child : children) {
				if (child.isAlive() && child.isEmptyChildren()) { //TODO rep propagada = (25 - 1.6 * edad) *0.01
					child.modifyReputation(val * 0.125f);
					child.propagateReputation(child, val * 0.1f);
				}
			}
		}
	}

	public static boolean[] agreeToPair(Individual indA, Individual indB, float moment) {
		boolean[] res = {!indA.hasPartner(), !indB.hasPartner()};
		float ageA = indA.getAge(moment);
		float attA = getGenderAttraction(indA, indB.getGender());
		float desA = getDesirability(indA);
		
		float ageB = indB.getAge(moment);
		float attB = getGenderAttraction(indB, indA.getGender());
		float desB = getDesirability(indB);

		if (ageA > (ageB - 6) * 2) {
			res[0] = false;
			res[1] = false;
		} else if (ageB > (ageA - 6) * 2) {
			res[0] = false;
			res[1] = false;
		}

		double[] rndv = {MyAIEngine.RND.nextInt(101),MyAIEngine.RND.nextInt(101)};
		
		if (res[0] && ((attA * desB * 0.1f) < rndv[0])) {
			res[0] = false;
		}
		if (res[1] && ((attB * desA * 0.1f) < rndv[1])) {
			res[1] = false;
		}

		
//		System.out.println("y,"+indA.getIndividualID()+","+ageA + "," +attA + "," +desA + "," +ageB + "," +attB + "," +desB + "," +rndv[0] + "," +rndv[1] + "," +res[0] + "," +res[1]);
		return res;
	}

	public static List<City> getAvailableMigrationTargets(Individual individual) {
		List<City> res = new LinkedList<City>();
		City curr = individual.getCurrentCity();
		String[] aux;
		if (curr != null && (aux = curr.getAdjacentCityIDs()) != null) {
			for (String str : aux) if (str != null) res.add(Arch.getCityById(str));
		}
		return res;
	}

	public static void killIndividual(Individual individual, float deathDate) {
		Individual partn = individual.getPartner();
		if (partn != null && individual.getIndividualID().equals(partn.getPartnerID())) {
			partn.setPartnerID(null);
		}
		
		City ccity = individual.getCurrentCity();
		if (ccity != null) {
			ccity.procIndividualDeath(individual, deathDate);
		}
		
		individual.setDeathDate(deathDate);
	}

	public static void migrateTo(Individual individual, City nucity) {
		City oldcity = individual.getCurrentCity();
		String[]zones = {null,null};
		boolean modRep = true;
		if (nucity != null) {
			nucity.addCitizen(individual.getIndividualID());
			zones[1] = nucity.getParentZoneID();
		} else {
			modRep = false;
		}
		
		if (oldcity != null) {
			oldcity.removeCitizen(individual.getIndividualID());
			zones[0] = oldcity.getParentZoneID();
			
			if (modRep) {
				if (zones[1].equals(zones[0])
						|| zones[1].equals(individual.getOriginalZoneID())) {
					modRep = false;
				}
			}
		}
		
		if (modRep) {
			float repPenalty = -0.25f * individual.getRep();
			individual.modifyReputation(repPenalty);
		}
		
	}

	public static float getDesirability(Individual individual) {
		short[] caracs = individual.getCharactValues();
		float res=individual.getRep();
		for (short s : caracs) {
			res += (s * 0.1f);
		}
		return res;
	}
	public static float getGenderAttraction(Individual individual, Gender target) {
		short sexOrientation = individual.getSexOrientation();
		float auxnn = sexOrientation + 32f;
		if (individual.getGender() == target) auxnn = 64f - auxnn;
		return individual.getHorniness() * auxnn / Math.max(32f - sexOrientation, 32f + sexOrientation);
	}

	public static List<Individual> getAncestors(Individual individual, int lvl) {
		List<Individual> res = new LinkedList<Individual>();
		if (individual != null && lvl > 0) {
			Individual[]prns = individual.getParents();
			if(prns[0]!=null)
				res.add(prns[0]);
			if(prns[1]!=null)
				res.add(prns[1]);
			
			if(lvl > 1) {
				List<Individual> aux;
				aux = getAncestors(prns[0], lvl - 1);
				for (Individual individual3 : aux) {
					if (!res.contains(individual3)) {
						res.add(individual3);
					}
				}
				aux = getAncestors(prns[1], lvl - 1);
				for (Individual individual3 : aux) {
					if (!res.contains(individual3)) {
						res.add(individual3);
					}
				}
				
			}
		}
		return res;
	}

	public static List<Individual> getDescendants(Individual individual, int lvl) {
		List<Individual> res = new LinkedList<Individual>();
		if (individual != null && lvl > 0) {
			List<Individual> chls = individual.getChildren();
			res.addAll(chls);
			
			if(lvl > 1) {
				List<Individual> aux;
				for (Individual individual2 : chls) {
					aux = getDescendants(individual2, lvl - 1);
					for (Individual individual3 : aux) {
						if (!res.contains(individual3)) {
							res.add(individual3);
						}
					}
				}				
			}
		}
		return res;
	}

	public static List<Individual> getRelatives(Individual individual, int lvl) {
		List<String> res = new LinkedList<String>();
		List<Individual> aux = new LinkedList<Individual>();
		res.add(individual.getIndividualID());
		aux.add(individual);
		List<String> bux = new LinkedList<String>();
		List<String> cux;
		String nu;
		for (int i = 0; i <= lvl; i++) {
			for (Individual str : aux) {
				nu = str.getFatherID();
				if (nu != null && !res.contains(nu)) {
					res.add(nu);
					bux.add(nu);
				}
				nu = str.getMotherID();
				if (nu != null && !res.contains(nu)) {
					res.add(nu);
					bux.add(nu);
				}
				cux = individual.getChildrenID();
				for (String string : cux) {
					if (string != null && !res.contains(string)) {
						res.add(string);
						bux.add(string);
					}
				}
			}
			aux = Arch.getIndividualsById(bux);
			bux.clear();
		}
		return Arch.getIndividualsById(res);
	}

}
