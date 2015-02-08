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

	/*----------WORLD------------------------------------*/
	
	@Override
	public Zone getRandomZone(World world) {
		return MyAIEngineWorld.getRandomZone(world);
	}

	@Override
	public Zone getRandomZonePopWeighted(World world) {
		return MyAIEngineWorld.getRandomZonePopWeighted(world);
	}

	@Override
	public Zone getRandomZoneSizeWeighted(World world) {
		return MyAIEngineWorld.getRandomZoneSizeWeighted(world);
	}

	@Override
	public int getNCitizens(World world) {
		return MyAIEngineWorld.getNCitizens(world);
	}

	@Override
	public Individual getRandomCitizen(World world) {
		return MyAIEngineWorld.getRandomCitizen(world);
	}

	@Override
	public List<Individual> getRandomCitizens(World world, int n) {
		return MyAIEngineWorld.getRandomCitizens(world, n);
	}

	@Override
	public void updateZones(World world) {
		MyAIEngineWorld.updateZones(world);
	}

	@Override
	public void updateIndividuals(World world) {
		MyAIEngineWorld.updateIndividuals(world);
		
	}

	/*----------ZONE-------------------------------------*/

	@Override
	public City getRandomCity(Zone zone) {
		return MyAIEngineZone.getRandomCity(zone);
	}

	@Override
	public City getRandomCityPopWeighted(Zone zone) {
		return MyAIEngineZone.getRandomCity(zone);
	}

	@Override
	public int getNCitizens(Zone zone) {
		return MyAIEngineZone.getNCitizens(zone);
	}

	@Override
	public Individual getRandomCitizen(Zone zone) {
		return MyAIEngineZone.getRandomCitizen(zone);
	}

	@Override
	public List<Individual> getRandomCitizens(Zone zone, int n) {
		return MyAIEngineZone.getRandomCitizens(zone, n);
	}

	@Override
	public List<City> getAdjacentCities(Zone zone) {
		return MyAIEngineZone.getAdjacentCities(zone);
	}

	@Override
	public List<Zone> getAdjacentZones(Zone zone) {
		return MyAIEngineZone.getAdjacentZones(zone);
	}

	@Override
	public boolean isCityAdjacent(Zone zone, City adj) {
		return MyAIEngineZone.isCityAdjacent(zone, adj);
	}

	@Override
	public boolean isZoneAdjacent(Zone zone, Zone adj) {
		return MyAIEngineZone.isZoneAdjacent(zone, adj);
	}

	@Override
	public void updateIndividuals(Zone zone, float moment) {
		MyAIEngineZone.updateIndividuals(zone, moment);
	}

	/*----------LANG-------------------------------------*/

	@Override
	public String getRandomName(Language language, Gender gender) {
		return MyAIEngineLanguage.getRandomName(language, gender);
	}

	@Override
	public String getRandomSurname(Language language) {
		return MyAIEngineLanguage.getRandomSurname(language);
	}

	/*----------CITY-------------------------------------*/

	@Override
	public Individual getRandomCitizen(City city) {
		return MyAIEngineCity.getRandomCitizen(city);
	}

	@Override
	public List<Individual> getRandomCitizens(City city, int n) {
		return MyAIEngineCity.getRandomCitizens(city, n);
	}

	@Override
	public void changeParentZone(City city, Zone nZone) {
		MyAIEngineCity.changeParentZone(city, nZone);
	}

	@Override
	public void procIndividualDeath(City city, Individual individual, float deathDate) {
		MyAIEngineCity.procIndividualDeath(city, individual, deathDate);
	}

	/*----------INDIVIDUAL-------------------------------*/

	@Override
	public String getCurrentCityFromParents(Individual father, Individual mother) {
		return MyAIEngineIndividual.getCurrentCityFromParents(father, mother);
	}

	@Override
	public String[] getNameSurnamesFromParents(Gender gender,
			Individual father, Individual mother) {
		return MyAIEngineIndividual.getNameSurnamesFromParents(gender, father, mother);
	}

	@Override
	public float getRepInheritedFromParents(Individual father, Individual mother) {
		return MyAIEngineIndividual.getRepInheritedFromParents(father, mother);
	}

	@Override
	public void pairIndividuals(Individual indA, Individual indB) {
		MyAIEngineIndividual.pairIndividuals(indA, indB);
	}

	@Override
	public void divorce(Individual indA, Individual indB) {
		MyAIEngineIndividual.divorce(indA, indB);
	}

	@Override
	public void propagateReputation(Individual individual, float val) {
		MyAIEngineIndividual.propagateReputation(individual, val);
	}

	@Override
	public boolean[] agreeToPair(Individual indA, Individual indB, float moment) {
		return MyAIEngineIndividual.agreeToPair(indA, indB, moment);
	}

	@Override
	public List<City> getAvailableMigrationTargets(Individual individual) {
		return MyAIEngineIndividual.getAvailableMigrationTargets(individual);
	}

	@Override
	public void killIndividual(Individual individual, float deathDate) {
		MyAIEngineIndividual.killIndividual(individual, deathDate);
	}

	@Override
	public void migrateTo(Individual individual, City nucity) {
		MyAIEngineIndividual.migrateTo(individual, nucity);
	}

	@Override
	public float getDesirability(Individual individual) {
		return MyAIEngineIndividual.getDesirability(individual);
	}

	@Override
	public float getGenderAttraction(Individual individual, Gender target) {
		return MyAIEngineIndividual.getGenderAttraction(individual, target);
	}

	@Override
	public float[] getGendersAttraction(Individual individual) {
		return MyAIEngineIndividual.getGendersAttraction(individual);
	}

	@Override
	public List<Individual> getAncestors(Individual individual, int lvl) {
		return MyAIEngineIndividual.getAncestors(individual, lvl);
	}

	@Override
	public List<Individual> getDescendants(Individual individual, int lvl) {
		return MyAIEngineIndividual.getDescendants(individual, lvl);
	}

	@Override
	public List<Individual> getRelatives(Individual individual, int lvl) {
		return MyAIEngineIndividual.getRelatives(individual, lvl);
	}

	@Override
	public boolean update(Individual individual, float moment) {
		return MyAIEngineIndividual.update(individual, moment);
	}

	/*----------GENOME-----------------------------------*/

	@Override
	public Gender getGender(Genome genome) {
		return MyAIEngineGenome.getGender(genome);
	}

	@Override
	public byte getSexOrientation(Genome genome) {
		return MyAIEngineGenome.getSexOrientation(genome);
	}

	@Override
	public short getStrength(Genome genome) {
		return MyAIEngineGenome.getStrength(genome);
	}

	@Override
	public short getConstitution(Genome genome) {
		return MyAIEngineGenome.getConstitution(genome);
	}

	@Override
	public short getSpeed(Genome genome) {
		return MyAIEngineGenome.getSpeed(genome);
	}

	@Override
	public short getIntelligence(Genome genome) {
		return MyAIEngineGenome.getIntelligence(genome);
	}

	@Override
	public short getWisdom(Genome genome) {
		return MyAIEngineGenome.getWisdom(genome);
	}

	@Override
	public short getCharisma(Genome genome) {
		return MyAIEngineGenome.getCharisma(genome);
	}

	@Override
	public short getBeauty(Genome genome) {
		return MyAIEngineGenome.getBeauty(genome);
	}

	@Override
	public short getFertility(Genome genome) {
		return MyAIEngineGenome.getFertility(genome);
	}

	@Override
	public short getHorniness(Genome genome) {
		return MyAIEngineGenome.getHorniness(genome);
	}

	@Override
	public short getComformity(Genome genome) {
		return MyAIEngineGenome.getComformity(genome);
	}

	@Override
	public double getChromosomeDominance(int chromosome, int gene) {
		return MyAIEngineGenome.getChromosomeDominance(chromosome, gene);
	}

	/*----------META-------------------------------------*/

	@Override
	public void finish() {
		// TODO finish() 
		
	}
	
	
}
