package ai_engine;

import model.Individual.Gender;
import model.Language;

public class MyAIEngineLanguage {

	public static String getRandomName(Language language, Gender gender) {
		// TODO getRandomName
		return ("[RANDOM "+gender+" NAME FROM LANG "+language.getLanguageID()+"]").intern();
	}

	public static String getRandomSurname(Language language) {
		// TODO getRandomSurname
		return ("[RANDOM SURNAME FROM LANG "+language.getLanguageID()+"]").intern();
	}

}
