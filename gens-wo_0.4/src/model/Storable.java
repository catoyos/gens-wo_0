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
//			return World.generateFromString(AIEngine.aie.getClearWorld(), string);
		case ZONE:
//			return Zone.generateFromString(AIEngine.aie.getClearZone(), string);
		case LANGUAGE:
//			return Zone.generateFromString(AIEngine.aie.getClearZone(), string);
		case CITY:
//			return City.generateFromString(AIEngine.aie.getClearCity(), string);
		case INDIVIDUAL:
//			return Individual.generateFromString(AIEngine.aie.getClearIndividual(), string);

		default: return null;
		}
	}
		
	public static String toFileString(Storable item){
		return item.toFileString();
	}
	
	public abstract String toFileString();
}
