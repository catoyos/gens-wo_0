package model;

import model.Individual.Gender;

public class Language extends Storable {

	private String languageID;
	
	public Language() {
		super(StorableType.LANGUAGE);
		// TODO public Language() 
	}

	public static void generateFromString(Language res, String str) {
		res.languageID = str;
		//TODO generateFromString
	}

	public static void generateNewLanguage(Language res, String zoneID) {
		res.languageID = zoneID;
		//TODO generateNewLanguage
	}

	public String getLanguageID() {
		return languageID;
	}
	
	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}

	public String getRandomName(Gender gender) {
		return Arch.aie == null ? null : Arch.aie.getRandomName(this, gender);
	}

	public String getRandomSurname() {
		return Arch.aie == null ? null : Arch.aie.getRandomSurname(this);
	}

	@Override
	public String toFileString() {
		return languageID;
	}

}
