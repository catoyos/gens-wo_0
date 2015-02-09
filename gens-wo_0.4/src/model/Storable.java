package model;

public abstract class Storable {
	public static enum StorableType { WORLD, ZONE, LANGUAGE, CITY, INDIVIDUAL }
	public final StorableType TYPE;
	public boolean modified = false;
	public byte instances = 0;
	
	public Storable(StorableType type){
		this.TYPE = type;
	}

	public static Storable generateFromString(String string, StorableType type){
		Storable res = null;
		switch (type) {
		case WORLD:
			res = Arch.getClearWorld();
			World.generateFromString((World) res, string);
			break;
		case ZONE:
			res = Arch.getClearZone();
			Zone.generateFromString((Zone) res, string);
			break;
		case LANGUAGE:
			res = Arch.getClearLanguage();
			Language.generateFromString((Language) res, string);
			break;
		case CITY:
			res = Arch.getClearCity();
			City.generateFromString((City) res, string);
			break;
		case INDIVIDUAL:
			res = Arch.getClearIndividual();
			Individual.generateFromString((Individual) res, string);
			break;
		}
		
		return res;
	}
		
	public static String toFileString(Storable item){
		return item.toFileString();
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);
	
	public abstract String toFileString();
	
}
