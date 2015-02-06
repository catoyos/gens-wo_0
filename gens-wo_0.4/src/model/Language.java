package model;

import model.Individual.Gender;

public class Language extends Storable {

	public Language() {
		super(StorableType.LANGUAGE);
		// TODO public Language() 
	}
	
	public static void generateFromString(Language res, String str) {
		if(res == null) res = new Language();
		//TODO generateFromString
	}

	public static void generateNewLanguage(Language res, String zoneID) {
		if(res == null) res = new Language();
		//TODO generateNewLanguage
	}

	public String getRandomName(Gender gender) {
		return Arch.aie == null ? null : Arch.aie.getRandomName(this ,gender);
	}

	public String getRandomSurname() {
		return Arch.aie == null ? null : Arch.aie.getRandomSurname(this);
	}

	@Override
	public String toFileString() {
		// TODO toFileString() {
		return null;
	}

}
