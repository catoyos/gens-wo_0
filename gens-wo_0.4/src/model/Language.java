package model;

public class Language extends Storable {

	public Language() {
		super(StorableType.LANGUAGE);
		// TODO Auto-generated constructor stub
	}
	
	public static Language generateFromString(Language res, String str) {
		if(res == null) res = new Language();
		//TODO
		return res;
	}

	@Override
	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}

}
