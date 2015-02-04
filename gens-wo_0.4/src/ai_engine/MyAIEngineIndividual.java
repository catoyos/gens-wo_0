package ai_engine;

import java.util.List;

import model.City;
import model.Individual;
import model.Individual.Gender;
import model.Language;

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
		Language lang = null;
		float difrep = father.getRep() - mother.getRep();
		if ((difrep > 0) || ((difrep == 0) && (father.getBirthDate() < mother.getBirthDate()))) {
			lang = father.getLanguage();
			res[1] = father.getSurnameA();
			res[2] = mother.getSurnameA();
		} else {
			lang = mother.getLanguage();
			res[1] = mother.getSurnameA();
			res[2] = father.getSurnameA();
		}
		res[0] = lang.getRandomName(gender);
		
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


	public static boolean[] agreeToPair(Individual indA, Individual indB, float moment) {
		boolean[] res = {!indA.hasPartner(), !indB.hasPartner()};
		float ageA = indA.getAge(moment);
		float attA = getGenderAttraction(indA, indB.getGender());
		float desA = getDesirability(indA);
		
		float ageB = indA.getAge(moment);
		float attB = getGenderAttraction(indB, indA.getGender());
		float desB = getDesirability(indB);

		if (ageA > (ageB - 6) * 2) {
			res[0] = false;
			res[1] = false;
		} else if (ageB > (ageA - 6) * 2) {
			res[0] = false;
			res[1] = false;
		}

		if (res[0] && ((attA * desB * 0.1f) < MyAIEngine.RND.nextFloat())) {
			res[0] = false;
		}
		if (res[1] && ((attB * desA * 0.1f) < MyAIEngine.RND.nextFloat())) {
			res[1] = false;
		}

		return res;
	}

	public static List<City> getAvailableMigrationTargets(Individual individual) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void killIndividual(Individual individual, float deathDate) {
		// TODO Auto-generated method stub

	}

	public static void migrateTo(Individual individual, City nucity) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Individual> getDescendants(Individual individual, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Individual> getRelatives(Individual individual, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}

}
