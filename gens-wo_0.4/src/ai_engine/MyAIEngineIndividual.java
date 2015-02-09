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
				Individual.divorce(indA, indA.getPartner());
			}
		}
		
		if (indB.hasPartner()) {
			if (!indB.getPartnerID().equals(indA.getIndividualID())) {
				Individual.divorce(indB.getPartner(), indB);
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

		if (ageA > (ageB - 5) * 2) {
			res[0] = false;
			res[1] = false;
		} else if (ageB > (ageA - 5) * 2) {
			res[0] = false;
			res[1] = false;
		}

		double[] rndv = {MyAIEngine.RND.nextInt(101), MyAIEngine.RND.nextInt(101)};
		
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
		float res = individual.getRep();
		for (short s : caracs) {
			res += (s * 0.1f);
		}
		return res;
	}
	
	public static float getGenderAttraction(Individual individual, Gender target) {
//		short sexOrientation = individual.getSexOrientation();
//		float auxnn = sexOrientation + 32f;
//		if (individual.getGender() == target) auxnn = 64f - auxnn;
//		return individual.getHorniness() * auxnn / Math.max(32f - sexOrientation, 32f + sexOrientation);
		int op = target == Gender.MALE ? 0 : 1;
		return  getGendersAttraction(individual)[op];
	}
	
	public static float[] getGendersAttraction(Individual individual) {
		float[] res = new float[2];
		short sexOrientation = individual.getSexOrientation();
		float auxnA = sexOrientation + 32f;
		float auxnB = 64f - auxnA;
		
		res[0] = individual.getHorniness() * Math.max(32f - sexOrientation, 32f + sexOrientation);
		res[1] = res[0];
		if (individual.getGender() == Gender.MALE) {
			res[0] *= auxnB;
			res[1] *= auxnA;
		} else {
			res[0] *= auxnA;
			res[1] *= auxnB;
		} 
		return res;
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
	
	public static int getFamilyDegree(Individual indA, Individual indB){
		int res = -1;
		String ida = indA.getIndividualID();
		String idb = indB.getIndividualID();
		if(ida.equals(idb)){
			res = 0;
		} else if (indA.containsChild(idb) || indB.containsChild(ida)) {
			res = 1;
		} else ;//TODO family degree
		return res;
	}

	public static boolean update(Individual individual, float moment) {
		if (!individual.isAlive()) {
			return false;
		}
		
		short[] caracts = individual.getCharactValues();
		/*
		 * 0: strength
		 * 1: constitution
		 * 2: speed
		 * 3: intelligence
		 * 4: wisdom
		 * 5: charisma
		 * 6: beauty
		 * 7: fertility
		 * 8: horniness
		 * 9: comformity
		 * 
		 */
		float age = individual.getAge(moment);

		float prDeath = 0.05f * (100 + age) / (25.0f + caracts[1]);
		if (prDeath > MyAIEngine.RND.nextFloat()) {
			individual.killIndividual(moment);
			System.out.println("----update: muerte");
			return true;
		}
		
		float repI = individual.getRep();
		int ncs = individual.getNChildren();
		City ct = individual.getCurrentCity();
		Individual partner;
		if (individual.hasPartner()) {
			partner = individual.getPartner();
			float prntAge = partner.getAge(moment);
			short prntFert = partner.getFertility();
			if (individual.getCurrentCityID().equals(partner.getCurrentCityID())
					&& age > (18 - caracts[7] * 0.1)
					&& age < (50 + caracts[7] * 0.3)
					&& prntAge > (18 - prntFert * 0.1)
					&& prntAge < (50 + prntFert * 0.3)) {
				if(processHavingAChild(individual, partner, moment, repI, ncs)) {
					System.out.println("----update: hijo");
					return true;
				}
			}

			float repP = partner.getRep();
			float prDivorce = 0.001f * ( caracts[4] * (repI - repP) / (ncs + 10f)
					+ 5 * (100 - caracts[4]) * (100 - caracts[9]) / (partner.getCharisma() + 5f));
			if (prDivorce > MyAIEngine.RND.nextFloat()) {
				Individual.divorce(individual, partner);
				System.out.println("----update: divorcio");
				return true;
			}
		} else if(age > 12){
			if (ct != null) {
				if(processFindAPartner(individual, moment, ct)) {
					System.out.println("----update: pareja");
					return true;
				}
			}
			
		}
		
		if (age > (15 + (caracts[4] - caracts[9]) * 0.05) && ct != null){
			if(processMigrate(individual, moment, ct)){
				return true;
			}
			
		}
				
		return false;
	}
	
	private static boolean processMigrate(Individual individual, float moment, City ct) {
		float prMove = 0.5f;//TODO
		if (prMove > MyAIEngine.RND.nextFloat()) {
			List<City> cts = getAvailableMigrationTargets(individual);
			if (!cts.isEmpty()) {
				City tgt = null;
				float points = 0;
				float auxpoints = 0;
				for (City city : cts) {
					auxpoints = evalueCityAsDestination(city);
					if(auxpoints > points){
						tgt = city;
						points = auxpoints;
					}
				}
				if (tgt != null) {
					if (agreeToMove(individual, tgt, moment)) {
						individual.migrateTo(tgt);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean agreeToMove(Individual individual, City tgt,
			float moment) {
		// TODO Auto-generated method stub
		return false;
	}

	private static float evalueCityAsDestination(City city) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static boolean processFindAPartner(Individual individual,
			float moment, City city) {
		//TODO mejor buscar pareja
		Individual partner;
		List<Individual> pret = city.getRandomCitizens(20);
		float[] indAttr = individual.getGendersAttraction();
		float auxDes = 0;
		partner = null;
		for (Individual ppartner : pret) {
			if (ppartner.getAge(moment) > 12
					&& 40 < (ppartner.getGender()==Gender.MALE ? indAttr[0] : indAttr[1])
					&& auxDes < ppartner.getDesirability()) {
				partner = ppartner;
				auxDes = partner.getDesirability();
			}
		}
		if (partner != null) {
			boolean[]acc = Individual.agreeToPair(individual, partner, moment);
			if (acc[0] && acc[1]) {
				Individual.pairIndividuals(individual, partner);
				return true;
			}
		}
		
		return false;
	}

	private static boolean processHavingAChild(Individual individual, Individual partner, float moment, float repI, int ncs) {
		//TODO tener en cuenta fertility, horniness, situacion de la zona
		float prChildren = repI * 0.5f + 1f;
		if (ncs == 0) {
			prChildren *= 0.5;
		} else if (ncs == 1) {
			prChildren *= 0.25;
		} else if (ncs > 5) {
			prChildren *= 0.01;
		} else {
			prChildren *= Math.pow(0.5, ncs + 1);
		}
		int iccp = individual.getCurrentCity().getNCitizens();
		int pccp = partner.getCurrentCity().getNCitizens();
		float prCityPop = 1f - (iccp + pccp) * 0.005f; //TODO manejar city pop BIEN
		if ((prChildren * prCityPop) > MyAIEngine.RND.nextFloat()) {
			Individual res = null;
			City c = null;
			if (individual.getGender() == Gender.MALE) {
				res = Arch.generateNewIndividual(individual, partner, moment);
			} else {
				res = Arch.generateNewIndividual(partner, individual, moment);
			}
			c = res.getCurrentCity();
			if (res != null && c != null) {
				c.addCitizen(res);
			}
			return true;
		}
		return false;
	}

}
