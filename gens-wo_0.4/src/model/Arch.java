package model;

import java.util.List;
import java.util.Random;

import model.interfaces.IAIEngine;
import model.interfaces.IStorage;

public final class Arch {
	protected static final Random RND = new Random();
	protected static IStorage sto;
	protected static IAIEngine aie;
		
	private Arch() throws Exception {
		throw new Exception("What are you doing tryin' to instantiate this? Stahp it pls.");
	}
	
	public static void setStorage(IStorage storage){
		Arch.sto = storage;
	}
	
	public static void setAIEngine(IAIEngine aiengine){
		Arch.aie = aiengine;
	}

	public static World getClearWorld() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearWorld();
		}
	}

	public static Zone getClearZone() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearZone();
		}
	}

	public static Language getClearLanguage() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearLanguage();
		}
	}

	public static City getClearCity() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearCity();
		}
	}

	public static Individual getClearIndividual() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearIndividual();
		}
	}

	public static String getId() {
		return sto.getId();
	}

	public static int getNWorlds() {
		return sto.getNWorlds();
	}

	public static List<String> getWorldIdsList() {
		return sto.getWorldIdsList();
	}

	public static List<World> getWorldsList() {
		return sto.getWorldsList();
	}

	public static World generateNewWorld(String uniID) {
		return sto.generateNewWorld(uniID);
	}

	public static World getWorldById(String itemid) {
		return sto.getWorldById(itemid);
	}

	public static List<World> getWorldsById(List<String> itemids) {
		return sto.getWorldsById(itemids);
	}

	public static World getWorldByIdFromMemory(String itemid) {
		return sto.getWorldByIdFromMemory(itemid);
	}

	public static List<World> getWorldsByIdFromMemory(List<String> itemids) {
		return sto.getWorldsByIdFromMemory(itemids);
	}

	public static World getWorldByIdFromFile(String itemid) {
		return sto.getWorldByIdFromFile(itemid);
	}

	public static List<World> getWorldsByIdFromFile(List<String> itemids) {
		return sto.getWorldsByIdFromFile(itemids);
	}

	public static void insertWorld(World item) {
		sto.insertWorld(item);
	}

	public static boolean worldExists(String itemid) {
		return sto.worldExists(itemid);
	}

	public static boolean isWorldInMemory(String itemid) {
		return sto.isWorldInMemory(itemid);
	}

	public static boolean isWorldInFile(String itemid) {
		return sto.isWorldInFile(itemid);
	}

	public static int getNZones() {
		return sto.getNZones();
	}

	public static List<String> getZoneIdsList() {
		return sto.getZoneIdsList();
	}

	public static List<Zone> getZonesList() {
		return sto.getZonesList();
	}

	public static Zone generateNewZone(String worldID) {
		return sto.generateNewZone(worldID);
	}

	public static Zone getZoneById(String itemid) {
		return sto.getZoneById(itemid);
	}

	public static List<Zone> getZonesById(List<String> itemids) {
		return sto.getZonesById(itemids);
	}

	public static Zone getZoneByIdFromMemory(String itemid) {
		return sto.getZoneByIdFromMemory(itemid);
	}

	public static List<Zone> getZonesByIdFromMemory(List<String> itemids) {
		return sto.getZonesByIdFromMemory(itemids);
	}

	public static Zone getZoneByIdFromFile(String itemid) {
		return sto.getZoneByIdFromFile(itemid);
	}

	public static List<Zone> getZonesByIdFromFile(List<String> itemids) {
		return sto.getZonesByIdFromFile(itemids);
	}

	public static void insertZone(Zone item) {
		sto.insertZone(item);
	}

	public static boolean zoneExists(String itemid) {
		return sto.zoneExists(itemid);
	}

	public static boolean isZoneInMemory(String itemid) {
		return sto.isZoneInMemory(itemid);
	}

	public static boolean isZoneInFile(String itemid) {
		return sto.isZoneInFile(itemid);
	}

	public static int getNLanguages() {
		return sto.getNLanguages();
	}

	public static List<String> getLanguageIdsList() {
		return sto.getLanguageIdsList();
	}

	public static List<Language> getLanguagesList() {
		return sto.getLanguagesList();
	}

	public static Language generateNewLanguage(String zoneID) {
		return sto.generateNewLanguage(zoneID);
	}

	public static Language getLanguageById(String itemid) {
		return sto.getLanguageById(itemid);
	}

	public static List<Language> getLanguagesById(List<String> itemids) {
		return sto.getLanguagesById(itemids);
	}

	public static Language getLanguageByIdFromMemory(String itemid) {
		return sto.getLanguageByIdFromMemory(itemid);
	}

	public static List<Language> getLanguagesByIdFromMemory(List<String> itemids) {
		return sto.getLanguagesByIdFromMemory(itemids);
	}

	public static Language getLanguageByIdFromFile(String itemid) {
		return sto.getLanguageByIdFromFile(itemid);
	}

	public static List<Language> getLanguagesByIdFromFile(List<String> itemids) {
		return sto.getLanguagesByIdFromFile(itemids);
	}

	public static void insertLanguage(Language item) {
		sto.insertLanguage(item);
	}

	public static boolean languageExists(String itemid) {
		return sto.languageExists(itemid);
	}

	public static boolean isLanguageInMemory(String itemid) {
		return sto.isLanguageInMemory(itemid);
	}

	public static boolean isLanguageInFile(String itemid) {
		return sto.isLanguageInFile(itemid);
	}

	public static int getNCities() {
		return sto.getNCities();
	}

	public static List<String> getCityIdsList() {
		return sto.getCityIdsList();
	}

	public static List<City> getCitiesList() {
		return sto.getCitiesList();
	}

	public static City generateNewCity(String worldID, String parentZoneID) {
		return sto.generateNewCity(worldID, parentZoneID);
	}

	public static City getCityById(String itemid) {
		return sto.getCityById(itemid);
	}

	public static List<City> getCitiesById(List<String> itemids) {
		return sto.getCitiesById(itemids);
	}

	public static City getCityByIdFromMemory(String itemid) {
		return sto.getCityByIdFromMemory(itemid);
	}

	public static List<City> getCitiesByIdFromMemory(List<String> itemids) {
		return sto.getCitiesByIdFromMemory(itemids);
	}

	public static City getCityByIdFromFile(String itemid) {
		return sto.getCityByIdFromFile(itemid);
	}

	public static List<City> getCitiesByIdFromFile(List<String> itemids) {
		return sto.getCitiesByIdFromFile(itemids);
	}

	public static void insertCity(City item) {
		sto.insertCity(item);
	}

	public static boolean cityExists(String itemid) {
		return sto.cityExists(itemid);
	}

	public static boolean isCityInMemory(String itemid) {
		return sto.isCityInMemory(itemid);
	}

	public static boolean isCityInFile(String itemid) {
		return sto.isCityInFile(itemid);
	}

	public static int getNIndividuals() {
		return sto.getNIndividuals();
	}

	public static List<String> getIndividualIdsList() {
		return sto.getIndividualIdsList();
	}

	public static List<Individual> getIndividualsList() {
		return sto.getIndividualsList();
	}

	public static Individual generateNewIndividual(City city, float birthDate) {
		return sto.generateNewIndividual(city, birthDate);
	}

	public static Individual generateNewIndividual(Individual father,
			Individual mother, float birthDate) {
		return sto.generateNewIndividual(father, mother, birthDate);
	}

	public static Individual getIndividualById(String itemid) {
		return sto.getIndividualById(itemid);
	}

	public static List<Individual> getIndividualsById(List<String> itemids) {
		return sto.getIndividualsById(itemids);
	}

	public static Individual getIndividualByIdFromMemory(String itemid) {
		return sto.getIndividualByIdFromMemory(itemid);
	}

	public static List<Individual> getIndividualsByIdFromMemory(List<String> itemids) {
		return sto.getIndividualsByIdFromMemory(itemids);
	}

	public static Individual getIndividualByIdFromFile(String itemid) {
		return sto.getIndividualByIdFromFile(itemid);
	}

	public static List<Individual> getIndividualsByIdFromFile(List<String> itemids) {
		return sto.getIndividualsByIdFromFile(itemids);
	}

	public static void insertIndividual(Individual item) {
		sto.insertIndividual(item);
	}

	public static boolean individualExists(String itemid) {
		return sto.individualExists(itemid);
	}

	public static boolean isIndividualInMemory(String itemid) {
		return sto.isIndividualInMemory(itemid);
	}

	public static boolean isIndividualInFile(String itemid) {
		return sto.isIndividualInFile(itemid);
	}

	public static void finish() {
		sto.finish();
		aie.finish();
	}
	
	
}
