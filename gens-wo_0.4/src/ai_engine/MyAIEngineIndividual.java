package ai_engine;

import model.Individual;
import model.Individual.Gender;

public class MyAIEngineIndividual {

	public static float individualGetGenderAttraction(Individual individual, Gender target) {
		short sexOrientation = individual.getSexOrientation();
		float auxnn = sexOrientation + 32f;
		if (individual.getGender() == target) auxnn = 64f - auxnn;
		return individual.getHorniness() * auxnn / Math.max(32f - sexOrientation, 32f + sexOrientation);
	}


}
