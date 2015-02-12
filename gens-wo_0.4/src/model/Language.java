package model;

import model.Individual.Gender;

public class Language extends Storable {

	private static final String FILESTRING_SEP = ",".intern();
	private static final String LIST_SEP = "@".intern();
	private static final String EMPTY_ID = "".intern();

	private String languageID;
	
	public Language() {
		super(StorableType.LANGUAGE);
		this.languageID = EMPTY_ID;
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

	public void clear() {		
		super.modified = false;
		this.languageID = EMPTY_ID;
	}

	public String getLanguageID() {
		return languageID;
	}
	
//	public void setLanguageID(String languageID) {
//		super.modified = true;
//		this.languageID = languageID;
//	}

	public boolean isID(String oID) {
		return oID != null && this.languageID.equals(oID) && !this.languageID.equals(EMPTY_ID);
	}	

	public String getRandomName(Gender gender) {
		return Arch.aie == null ? null : Arch.aie.getRandomName(this, gender);
	}

	public String getRandomSurname() {
		return Arch.aie == null ? null : Arch.aie.getRandomSurname(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageID == null) ?
				0 : languageID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (languageID == null) {
			if (other.languageID != null)
				return false;
		} else if (!languageID.equals(other.languageID))
			return false;
		return true;
	}

	@Override
	public String toFileString() {
		return languageID;
	}

}
