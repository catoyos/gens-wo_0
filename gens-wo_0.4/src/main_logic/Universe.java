package main_logic;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import main_logic.io.InputOutput;
import model.Arch;
import model.City;
import model.City.Direction;
import model.Individual;
import model.Individual.Gender;
import model.World;
import model.Zone;
import model.interfaces.IAIEngine;
import model.utils.StringUtils;
import data_storage.Storage;

public class Universe {
	private static final String UNIVERSE_FILE = "universe.dat";
//	private IStorage st;
	final String uniID;
	private List<String> worldIDs;
	private World currentWorld;
	
	public Universe(IAIEngine aieng) {
		this(generateId(), aieng);
	}

	public Universe(String uniID, IAIEngine aieng) {
		this.uniID = uniID;
		this.worldIDs = new LinkedList<String>();
		this.currentWorld = null;
		Arch.setAIEngine(aieng);
		Storage.getInitiatedStorage(uniID);
	}

	private static String generateId(){
		return StringUtils.generateId((int)(Math.random() * StringUtils.ID_N_25_3), 3);
	}
	
//	public static Universe generateFromString(String universeFile) {
//		return generateFromString(universeFile, null);
//	}
	
	public static Universe generateFromString(String universeFile, IAIEngine aieng) {
		String[] lines = universeFile.split("\n");
		Universe uni = null;
		try {
			String nuid = lines[0];
			String[] widsarr = null;
			String cuid = null;
			
			uni = new Universe(nuid, aieng);
			
			if(lines.length > 1){
				widsarr = lines[1].split(",");
				if(lines.length > 2){
					cuid = lines[2];
				}
				for (String wids : widsarr) {
					if (Arch.worldExists(wids)) {
						uni.worldIDs.add(wids);
						if (wids.equals(cuid)) {
							uni.currentWorld = Arch.getWorldById(wids);
						}
					}
				}
			}
			if(lines.length > 3){
				String[] nids = lines[3].split(",");
				try {
					World.setNewIDn(Long.parseLong(nids[0]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				try {
					Zone.setNewIDn(Long.parseLong(nids[1]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				try {
					City.setNewIDn(Long.parseLong(nids[2]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				try {
					Individual.setNewIDn(Long.parseLong(nids[3]));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uni;
	}

	public static File getUniverseFile(String id) {
		return new File(InputOutput.getDefaultFilePath(InputOutput.getDefaultFolderPath(id), UNIVERSE_FILE));
	}

	public static void setAIEngine(IAIEngine aieng) {
		Arch.setAIEngine(aieng);
	}

	public List<String> getWorldIDs() {
		return worldIDs;
	}

	public void setWorldIDs(List<String> worldIDs) {
		this.worldIDs = worldIDs;
	}

	public World getCurrentWorld() {
		return currentWorld;
	}

	public void setCurrentWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}

	public boolean isCurrentSet() {
		return currentWorld != null;
	}

	public boolean hasWorlds() {
		return !worldIDs.isEmpty();
	}

	public String getWorldInfoLine(String world) {
		return Arch.getWorldById(world).toFileString();
	}

	public World getWorld(String world) {
		World w	= null;
		for (String string : worldIDs) {
			if (string.equalsIgnoreCase(world)) {
				w = Arch.getWorldById(string);
			}
		}
		return w;
	}

	public String generateNewWorld() {
		World neww = Arch.generateNewWorld(uniID);
		initWorld(neww, 4);
		String res = neww.getWorldID();
		worldIDs.add(res);
		return res;
	}
	
	
	public void initWorld(World world, int size) {
		Zone zz = Arch.generateNewZone(world.getWorldID());
		initZone(zz);
		world.addZoneId(zz.getZoneID());

		City[][] cities = new City[size][size];
		for (int i = 0; i < cities.length; i++) {
			for (int j = 0; j < cities[0].length; j++) {
				cities[i][j] = Arch.generateNewCity(world.getWorldID(), zz.getZoneID());
				initCity(cities[i][j]);
			}
		}

//		List<City> caux = new LinkedList<City>();
		for (int i = 0; i < cities.length; i++) {
			for (int j = 0; j < cities[0].length; j++) {
				
				City.setAdjacentCities(cities[i][j], Direction.NORTH, cities[(i + size - 1) % size][j]);
				City.setAdjacentCities(cities[i][j], Direction.EAST, cities[i][(j + size + 1) % size]);
				City.setAdjacentCities(cities[i][j], Direction.SOUTH, cities[(i + size + 1) % size][j]);
				City.setAdjacentCities(cities[i][j], Direction.WEST, cities[i][(j + size - 1) % size]);
				
//				acs[0] = cities[(i + size - 1) % size][j];
//				acs[1] = cities[i][(j + size + 1) % size];
//				acs[2] = cities[(i + size + 1) % size][j];
//				acs[3] = cities[i][(j + size - 1) % size];
//				
//				cities[i][j].setAdjacentCities(acs);
				zz.addCity(cities[i][j]);
//				caux.add(cities[i][j]);
			}
		}
		
		Individual iaux;
		City cc;
		int n = 100;
		float lapse = 20f / n;
		for (int i = 0; i < n - 50; i++) {
			cc = zz.getRandomCity();
			iaux = Arch.generateNewIndividual(cc, world.getMoment());
			initIndividual(iaux);
			cc.addCitizen(iaux);
			world.modifyMoment(lapse);
		}

		Individual father = null;
		Individual mother = null;
		List<Individual> rndIndList = zz.getRandomCitizens(30);
		for (int i = 0; i < 50; i++) {
			
			if (rndIndList.isEmpty()) {
				rndIndList.addAll(zz.getRandomCitizens(10));
			}
			
			if (rndIndList.get(0).getGender() == Gender.MALE) {
				father = rndIndList.get(0);
			} else {
				mother = rndIndList.get(0);
			}
			rndIndList.remove(0);
			do {
				for (int j = 0; j < rndIndList.size() && (father == null || mother == null); j++) {
					Individual individual = rndIndList.get(j);
					if (individual.getGender() == (father == null ? Gender.MALE : Gender.FEMALE)) {
						if (father == null) {
							father = individual;
						} else {
							mother = individual;
						}
						rndIndList.remove(j);
					}
				}
				if (father == null || mother == null) {
					rndIndList.addAll(zz.getRandomCitizens(10));
				}
			} while(father == null || mother == null);

			iaux = Arch.generateNewIndividual(father, mother, world.getMoment());
			initIndividual(iaux);
			cc = Arch.getCityById(iaux.getCurrentCityID());
			cc.addCitizen(iaux);
			world.modifyMoment(lapse);

			father = null;
			mother = null;
		}
//		zz.updateIndividuals(world.getMoment());
		world.updateIndividuals();
	}

	public void initZone(Zone zone) {;}

	public void initCity(City city) {;}

	public void initIndividual(Individual individual) {;}

	public void modifyCurrentWorldMoment(float lapse) {
		currentWorld.modifyMoment(lapse);
	}

	public float getCurrentWorldMoment() {
		return currentWorld.getMoment();
	}

	public void updateCurrentWorldZones() {
		currentWorld.updateZones();
	}

	public void updateCurrentWorldIndividuals() {
		currentWorld.updateIndividuals();
	}

	public int getNCurrentCitizens() {
		return currentWorld.getNCitizens();
	}

	public Individual getRandomCurrentCitizen() {
		return currentWorld.getRandomCitizen();
	}

	public List<Individual> getRandomCurrentCitizens(int n) {
		return currentWorld.getRandomCitizens(n);
	}

	public void spreadWealth(double lapse) {
		// TODO Auto-generated method stub
		int kf = 14;
		int kfsq = kf * kf;
		int lf = 1400;
		int cs = getNCurrentCitizens();
		int reps = cs;
		if (cs < kf) {
			reps *= cs;
		} else if (cs < (2 * kfsq)) {
			reps = kfsq;
		} else if (cs < (2 * lf)) {
			reps = cs / 2;
		} else {
			reps = lf;
		}
		reps *= lapse;
		
		float val = 0;
		List<Individual> lst = getRandomCurrentCitizens(reps);
		for (int i = 0; i < lst.size(); i++) {
			Individual individual = lst.get(i);
			val = (individual.getCharactValues()[i % 9]) / 100f;
//			val = 1;
			individual.modifyReputation(val);
		}

	}

	public void finish() {
		try {
			List<String> records = toFileStrings();
			InputOutput.printRecordsToFile(records, getUniverseFile(uniID));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Arch.finish();
	}

	public List<String> toFileStrings() {
		List<String> records = new LinkedList<String>();
		records.add(uniID);
		if (hasWorlds()) {
			String wids = worldIDs.get(0);
			for (int i = 1; i < worldIDs.size(); i++) {
				wids += "," + worldIDs.get(i);
			}
			records.add(wids);
			if (currentWorld != null) {
				records.add(currentWorld.getWorldID());
			}
		} else {
			records.add("");
		}
		records.add(World.getNewIDn() + "," + Zone.getNewIDn()+","+City.getNewIDn()+","+Individual.getNewIDn());
		return records;
	}

	
	
	@Override
	public String toString() {
		return "uniID=" + uniID
				+ "\nworldIDs=" + worldIDs.toString()
				+ "\ncurrentWorld=" + currentWorld;
	}
}