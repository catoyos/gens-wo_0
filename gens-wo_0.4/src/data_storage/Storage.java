package data_storage;

import java.io.IOException;
import java.util.List;

import model.Arch;
import model.City;
import model.Individual;
import model.Language;
import model.Storable;
import model.World;
import model.Zone;
import model.interfaces.IStorage;

public class Storage implements IStorage {
	private String id;
	private WorldStorage ws;
	private ZoneStorage zs;
	private LanguageStorage ls;
	private CityStorage cs;
	private IndividualStorage is;

	private Storage(String id) {		
		this.id = id;
		this.ws = new WorldStorage(id);
		this.zs = new ZoneStorage(id);
		this.ls = new LanguageStorage(id);
		this.cs = new CityStorage(id);
		this.is = new IndividualStorage(id);
	}

	public static IStorage getUninitiatedStorage(String uniID) {
		return new Storage(uniID);
	}

	public static IStorage getInitiatedStorage(String uniID) {
		Storage st = new Storage(uniID);
		Storage.initiateStorage(st);
		return st;
	}

	public static void initiateStorage(Storage st) {
		Arch.setStorage(st);
		st.initialLoad();
	}
	
	public void initialLoad() {
		loadStorage(ws);
		loadStorage(zs);
		loadStorage(ls);
		loadStorage(cs);
		loadStorage(is);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadStorage(AbstractStorage store) {
		String data;
		String[] dataLines;
		try {
			data = store.loadFileToString();
			dataLines = data.split("\n");
			Storable aux = null;
			for (String string : dataLines) {
				if (!string.equals("")) {
					try {
						aux = Storable.generateFromString(string, store.TYPE);
						if (aux != null) {
							store.insertItem(aux);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see data_storage.IStorage#getId()
	 */
	@Override
	public String getId(){
		return id;
	}
	
	/* (non-Javadoc)
	 * @see data_storage.IStorage#getNWorlds()
	 */
	@Override
	public int getNWorlds() {
		return ws.getNItems();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldIdsList()
	 */
	@Override
	public List<String> getWorldIdsList() {
		return ws.getItemIdsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldsList()
	 */
	@Override
	public List<World> getWorldsList() {
		return ws.getItemsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getClearWorld()
	 */
	@Override
	public World getClearWorld() {
		return ws.getClearItem();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#generateNewWorld(java.lang.String)
	 */
	@Override
	public World generateNewWorld(String uniID) {
		return ws.generateNewItem(uniID);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldById(java.lang.String)
	 */
	@Override
	public World getWorldById(String itemid) {
		return ws.getItemById(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldsById(java.util.List)
	 */
	@Override
	public List<World> getWorldsById(List<String> itemids) {
		return ws.getItemsById(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldByIdFromMemory(java.lang.String)
	 */
	@Override
	public World getWorldByIdFromMemory(String itemid) {
		return ws.getItemByIdFromMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldsByIdFromMemory(java.util.List)
	 */
	@Override
	public List<World> getWorldsByIdFromMemory(List<String> itemids) {
		return ws.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldByIdFromFile(java.lang.String)
	 */
	@Override
	public World getWorldByIdFromFile(String itemid) {
		return ws.getItemByIdFromFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getWorldsByIdFromFile(java.util.List)
	 */
	@Override
	public List<World> getWorldsByIdFromFile(List<String> itemids) {
		return ws.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#insertWorld(model.World)
	 */
	@Override
	public void insertWorld(World item) {
		ws.insertItem(item);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#worldExists(java.lang.String)
	 */
	@Override
	public boolean worldExists(String itemid) {
		return ws.exists(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isWorldInMemory(java.lang.String)
	 */
	@Override
	public boolean isWorldInMemory(String itemid) {
		return ws.isInMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isWorldInFile(java.lang.String)
	 */
	@Override
	public boolean isWorldInFile(String itemid) {
		return ws.isInFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getNZones()
	 */
	@Override
	public int getNZones() {
		return zs.getNItems();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZoneIdsList()
	 */
	@Override
	public List<String> getZoneIdsList() {
		return zs.getItemIdsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZonesList()
	 */
	@Override
	public List<Zone> getZonesList() {
		return zs.getItemsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getClearZone()
	 */
	@Override
	public Zone getClearZone() {
		return zs.getClearItem();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#generateNewZone(java.lang.String)
	 */
	@Override
	public Zone generateNewZone(String worldID) {
		return zs.generateNewItem(worldID);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZoneById(java.lang.String)
	 */
	@Override
	public Zone getZoneById(String itemid) {
		return zs.getItemById(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZonesById(java.util.List)
	 */
	@Override
	public List<Zone> getZonesById(List<String> itemids) {
		return zs.getItemsById(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZoneByIdFromMemory(java.lang.String)
	 */
	@Override
	public Zone getZoneByIdFromMemory(String itemid) {
		return zs.getItemByIdFromMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZonesByIdFromMemory(java.util.List)
	 */
	@Override
	public List<Zone> getZonesByIdFromMemory(List<String> itemids) {
		return zs.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZoneByIdFromFile(java.lang.String)
	 */
	@Override
	public Zone getZoneByIdFromFile(String itemid) {
		return zs.getItemByIdFromFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getZonesByIdFromFile(java.util.List)
	 */
	@Override
	public List<Zone> getZonesByIdFromFile(List<String> itemids) {
		return zs.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#insertZone(model.Zone)
	 */
	@Override
	public void insertZone(Zone item) {
		zs.insertItem(item);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#zoneExists(java.lang.String)
	 */
	@Override
	public boolean zoneExists(String itemid) {
		return zs.exists(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isZoneInMemory(java.lang.String)
	 */
	@Override
	public boolean isZoneInMemory(String itemid) {
		return zs.isInMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isZoneInFile(java.lang.String)
	 */
	@Override
	public boolean isZoneInFile(String itemid) {
		return zs.isInFile(itemid);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getNLanguages()
	 */
	@Override
	public int getNLanguages() {
		return ls.getNItems();
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getLanguageIdsList()
	 */
	@Override
	public List<String> getLanguageIdsList() {
		return ls.getItemIdsList();
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getLanguagesList()
	 */
	@Override
	public List<Language> getLanguagesList() {
		return ls.getItemsList();
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getClearLanguage()
	 */
	@Override
	public Language getClearLanguage() {
		return ls.getClearItem();
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#generateNewLanguage(java.lang.String)
	 */
	@Override
	public Language generateNewLanguage(String zoneID) {
		return ls.generateNewItem(zoneID);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getLanguageById(java.lang.String)
	 */
	@Override
	public Language getLanguageById(String itemid) {
		return ls.getItemById(itemid);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#getLanguagesById(java.util.List)
	 */
	@Override
	public List<Language> getLanguagesById(List<String> itemids) {
		return ls.getItemsById(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getLanguageByIdFromMemory(java.lang.String)
	 */
	@Override
	public Language getLanguageByIdFromMemory(String itemid) {
		return ls.getItemByIdFromMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getLanguagesByIdFromMemory(java.util.List)
	 */
	@Override
	public List<Language> getLanguagesByIdFromMemory(List<String> itemids) {
		return ls.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getLanguageByIdFromFile(java.lang.String)
	 */
	@Override
	public Language getLanguageByIdFromFile(String itemid) {
		return ls.getItemByIdFromFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getLanguagesByIdFromFile(java.util.List)
	 */
	@Override
	public List<Language> getLanguagesByIdFromFile(List<String> itemids) {
		return ls.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#insertLanguage(model.Language)
	 */
	@Override
	public void insertLanguage(Language item) {
		ls.insertItem(item);		
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#languageExists(java.lang.String)
	 */
	@Override
	public boolean languageExists(String itemid) {
		return ls.exists(itemid);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#isLanguageInMemory(java.lang.String)
	 */
	@Override
	public boolean isLanguageInMemory(String itemid) {
		return ls.isInMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IStorage#isLanguageInFile(java.lang.String)
	 */
	@Override
	public boolean isLanguageInFile(String itemid) {
		return ls.isInFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getNCities()
	 */
	@Override
	public int getNCities() {
		return cs.getNItems();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCityIdsList()
	 */
	@Override
	public List<String> getCityIdsList() {
		return cs.getItemIdsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCitiesList()
	 */
	@Override
	public List<City> getCitiesList() {
		return cs.getItemsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getClearCity()
	 */
	@Override
	public City getClearCity() {
		return cs.getClearItem();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#generateNewCity(java.lang.String, java.lang.String)
	 */
	@Override
	public City generateNewCity(String worldID, String parentZoneID) {
		return cs.generateNewItem(worldID, parentZoneID);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCityById(java.lang.String)
	 */
	@Override
	public City getCityById(String itemid) {
		return cs.getItemById(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCitiesById(java.util.List)
	 */
	@Override
	public List<City> getCitiesById(List<String> itemids) {
		return cs.getItemsById(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCityByIdFromMemory(java.lang.String)
	 */
	@Override
	public City getCityByIdFromMemory(String itemid) {
		return cs.getItemByIdFromMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCitysByIdFromMemory(java.util.List)
	 */
	@Override
	public List<City> getCitiesByIdFromMemory(List<String> itemids) {
		return cs.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCityByIdFromFile(java.lang.String)
	 */
	@Override
	public City getCityByIdFromFile(String itemid) {
		return cs.getItemByIdFromFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getCitysByIdFromFile(java.util.List)
	 */
	@Override
	public List<City> getCitiesByIdFromFile(List<String> itemids) {
		return cs.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#insertCity(model.City)
	 */
	@Override
	public void insertCity(City item) {
		cs.insertItem(item);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#cityExists(java.lang.String)
	 */
	@Override
	public boolean cityExists(String itemid) {
		return cs.exists(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isCityInMemory(java.lang.String)
	 */
	@Override
	public boolean isCityInMemory(String itemid) {
		return cs.isInMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isCityInFile(java.lang.String)
	 */
	@Override
	public boolean isCityInFile(String itemid) {
		return cs.isInFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getNIndividuals()
	 */
	@Override
	public int getNIndividuals() {
		return is.getNItems();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualIdsList()
	 */
	@Override
	public List<String> getIndividualIdsList() {
		return is.getItemIdsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualsList()
	 */
	@Override
	public List<Individual> getIndividualsList() {
		return is.getItemsList();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getClearIndividual()
	 */
	@Override
	public Individual getClearIndividual() {
		return is.getClearItem();
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#generateNewIndividual(model.City, float)
	 */
	@Override
	public Individual generateNewIndividual(City city, float birthDate) {
		return is.generateNewItem(city, birthDate);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#generateNewIndividual(model.Individual, model.Individual, float)
	 */
	@Override
	public Individual generateNewIndividual(Individual father,
			Individual mother, float birthDate) {
		return is.generateNewItem(father, mother, birthDate);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualById(java.lang.String)
	 */
	@Override
	public Individual getIndividualById(String itemid) {
		return is.getItemById(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualsById(java.util.List)
	 */
	@Override
	public List<Individual> getIndividualsById(List<String> itemids) {
		return is.getItemsById(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualByIdFromMemory(java.lang.String)
	 */
	@Override
	public Individual getIndividualByIdFromMemory(String itemid) {
		return is.getItemByIdFromMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualsByIdFromMemory(java.util.List)
	 */
	@Override
	public List<Individual> getIndividualsByIdFromMemory(List<String> itemids) {
		return is.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualByIdFromFile(java.lang.String)
	 */
	@Override
	public Individual getIndividualByIdFromFile(String itemid) {
		return is.getItemByIdFromFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#getIndividualsByIdFromFile(java.util.List)
	 */
	@Override
	public List<Individual> getIndividualsByIdFromFile(List<String> itemids) {
		return is.getItemsByIdFromFile(itemids);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#insertIndividual(model.Individual)
	 */
	@Override
	public void insertIndividual(Individual item) {
		is.insertItem(item);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#individualExists(java.lang.String)
	 */
	@Override
	public boolean individualExists(String itemid) {
		return is.exists(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isIndividualInMemory(java.lang.String)
	 */
	@Override
	public boolean isIndividualInMemory(String itemid) {
		return is.isInMemory(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#isIndividualInFile(java.lang.String)
	 */
	@Override
	public boolean isIndividualInFile(String itemid) {
		return is.isInFile(itemid);
	}

	/* (non-Javadoc)
	 * @see data_storage.IStorage#finish()
	 */
	@Override
	public void finish() {
		ws.finish();
		zs.finish();
		ls.finish();
		cs.finish();
		is.finish();
	}

	public String toString() {
		return ws.toString() + "\n" + zs.toString() + "\n" + ls.toString() + "\n" + cs.toString() + "\n" + is.toString();
	}


}
