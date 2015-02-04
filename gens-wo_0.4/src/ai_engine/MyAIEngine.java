package ai_engine;

import java.util.List;
import java.util.Random;

import model.City;
import model.Genome;
import model.Individual;
import model.Individual.Gender;
import model.Language;
import model.World;
import model.Zone;
import model.interfaces.IAIEngine;

public class MyAIEngine implements IAIEngine {

	protected static final Random RND = new Random();
	
	@Override
	public Zone getRandomZone(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zone getRandomZonePopWeighted(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zone getRandomZoneSizeWeighted(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNCitizens(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Individual getRandomCitizen(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getRandomCitizens(World world, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateZones(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIndividuals(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City getRandomCity(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City getRandomCityPopWeighted(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNCitizens(Zone zone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Individual getRandomCitizen(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getRandomCitizens(Zone zone, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getAdjacentCities(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Zone> getAdjacentZones(Zone zone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCityAdjacent(Zone zone, City adj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZoneAdjacent(Zone zone, Zone adj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateIndividuals(Zone zone, float moment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Individual getRandomCitizen(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getRandomCitizens(City city, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentCityFromParents(Individual father, Individual mother) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getNameSurnamesFromParents(Gender gender,
			Individual father, Individual mother) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getRepInheritedFromParents(Individual father, Individual mother) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pairIndividuals(Individual indA, Individual indB) {
		// TODO Auto-generated method stub
	}

	@Override
	public void divorce(Individual indA, Individual indB) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean[] agreeToPair(Individual indA, Individual indB, float moment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getAvailableMigrationTargets(Individual individual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void killIndividual(Individual individual, float deathDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void migrateTo(Individual individual, City nucity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDesirability(Individual individual) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGenderAttraction(Individual individual, Gender target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Individual> getAncestors(Individual individual, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getDescendants(Individual individual, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Individual> getRelatives(Individual individual, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gender getGender(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getGender(genome);
	}

	@Override
	public byte getSexOrientation(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getSexOrientation(genome);
	}

	@Override
	public short getStrength(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getStrength(genome);
	}

	@Override
	public short getConstitution(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getConstitution(genome);
	}

	@Override
	public short getSpeed(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getSpeed(genome);
	}

	@Override
	public short getIntelligence(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getIntelligence(genome);
	}

	@Override
	public short getWisdom(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getWisdom(genome);
	}

	@Override
	public short getCharisma(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getCharisma(genome);
	}

	@Override
	public short getBeauty(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getBeauty(genome);
	}

	@Override
	public short getFertility(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getFertility(genome);
	}

	@Override
	public short getHorniness(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getHorniness(genome);
	}

	@Override
	public short getComformity(Genome genome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getComformity(genome);
	}

	@Override
	public double getChromosomeDominance(int chromosome) {
		// TODO Auto-generated method stub
		return MyAIEngineGenome.getChromosomeDominance(chromosome);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRandomName(Language language, Gender gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRandomSurname(Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeParentZone(City city, Zone nZone) {
		// TODO Auto-generated method stub
		
	}




}
