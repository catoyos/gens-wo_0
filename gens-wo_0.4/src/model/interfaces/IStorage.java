package model.interfaces;

import java.util.List;

import model.City;
import model.Individual;
import model.Language;
import model.World;
import model.Zone;

/**
 * @author catoyos
 * 
 */
public interface IStorage {

	/**
	 * @return
	 */
	public abstract String getId();

	/**
	 * @return
	 */
	public abstract int getNWorlds();

	/**
	 * @return
	 */
	public abstract List<String> getWorldIdsList();

	/**
	 * @return
	 */
	public abstract List<World> getWorldsList();

	/**
	 * @return
	 */
	public abstract World getClearWorld();

	/**
	 * @param uniID
	 * @return
	 */
	public abstract World generateNewWorld(String uniID);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract World getWorldById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<World> getWorldsById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract World getWorldByIdFromMemory(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<World> getWorldsByIdFromMemory(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract World getWorldByIdFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<World> getWorldsByIdFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	public abstract void insertWorld(World item);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean worldExists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isWorldInMemory(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isWorldInFile(String itemid);

	/**
	 * @return
	 */
	public abstract int getNZones();

	/**
	 * @return
	 */
	public abstract List<String> getZoneIdsList();

	/**
	 * @return
	 */
	public abstract List<Zone> getZonesList();

	/**
	 * @return
	 */
	public abstract Zone getClearZone();

	/**
	 * @param worldID
	 * @return
	 */
	public abstract Zone generateNewZone(String worldID);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Zone getZoneById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Zone> getZonesById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Zone getZoneByIdFromMemory(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Zone> getZonesByIdFromMemory(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Zone getZoneByIdFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Zone> getZonesByIdFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	public abstract void insertZone(Zone item);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean zoneExists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isZoneInMemory(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isZoneInFile(String itemid);

	/**
	 * @return
	 */
	public abstract int getNLanguages();

	/**
	 * @return
	 */
	public abstract List<String> getLanguageIdsList();

	/**
	 * @return
	 */
	public abstract List<Language> getLanguagesList();

	/**
	 * @return
	 */
	public abstract Language getClearLanguage();

	/**
	 * @param zoneID
	 * @return
	 */
	public abstract Language generateNewLanguage(String zoneID);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Language getLanguageById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Language> getLanguagesById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Language getLanguageByIdFromMemory(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Language> getLanguagesByIdFromMemory(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Language getLanguageByIdFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Language> getLanguagesByIdFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	public abstract void insertLanguage(Language item);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean languageExists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isLanguageInMemory(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isLanguageInFile(String itemid);

	/**
	 * @return
	 */
	public abstract int getNCities();

	/**
	 * @return
	 */
	public abstract List<String> getCityIdsList();

	/**
	 * @return
	 */
	public abstract List<City> getCitiesList();

	/**
	 * @return
	 */
	public abstract City getClearCity();

	/**
	 * @param worldID
	 * @param parentZoneID
	 * @return
	 */
	public abstract City generateNewCity(String worldID, String parentZoneID);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract City getCityById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<City> getCitiesById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract City getCityByIdFromMemory(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<City> getCitiesByIdFromMemory(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract City getCityByIdFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<City> getCitiesByIdFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	public abstract void insertCity(City item);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean cityExists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isCityInMemory(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isCityInFile(String itemid);

	/**
	 * @return
	 */
	public abstract int getNIndividuals();

	/**
	 * @return
	 */
	public abstract List<String> getIndividualIdsList();

	/**
	 * @return
	 */
	public abstract List<Individual> getIndividualsList();

	/**
	 * @return
	 */
	public abstract Individual getClearIndividual();

	/**
	 * @param city
	 * @param birthDate
	 * @return
	 */
	public abstract Individual generateNewIndividual(City city, float birthDate);

	/**
	 * @param father
	 * @param mother
	 * @param birthDate
	 * @return
	 */
	public abstract Individual generateNewIndividual(Individual father,
			Individual mother, float birthDate);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Individual getIndividualById(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Individual> getIndividualsById(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Individual getIndividualByIdFromMemory(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Individual> getIndividualsByIdFromMemory(List<String> itemids);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract Individual getIndividualByIdFromFile(String itemid);

	/**
	 * @param itemids
	 * @return
	 */
	public abstract List<Individual> getIndividualsByIdFromFile(List<String> itemids);

	/**
	 * @param item
	 */
	public abstract void insertIndividual(Individual item);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean individualExists(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isIndividualInMemory(String itemid);

	/**
	 * @param itemid
	 * @return
	 */
	public abstract boolean isIndividualInFile(String itemid);

	/**
	 * 
	 */
	public abstract void finish();

}