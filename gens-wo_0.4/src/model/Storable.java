package model;


public abstract class Storable {
	public static enum StorableType { WORLD, ZONE, LANGUAGE, CITY, INDIVIDUAL }
	public final StorableType TYPE;
	
	public Storable(StorableType type){
		this.TYPE = type;
	}

	public static Storable generateFromString(String string, StorableType type){
		switch (type) {
		case WORLD:
			return World.generateFromString(Arch.sto.getClearWorld(), string);
		case ZONE:
			return Zone.generateFromString(Arch.sto.getClearZone(), string);
		case LANGUAGE:
			return Language.generateFromString(Arch.sto.getClearLanguage(), string);
		case CITY:
			return City.generateFromString(Arch.sto.getClearCity(), string);
		case INDIVIDUAL:
			return Individual.generateFromString(Arch.sto.getClearIndividual(), string);

		default: return null;
		}
	}
		
	public static String toFileString(Storable item){
		return item.toFileString();
	}
	
	public abstract String toFileString();
}
