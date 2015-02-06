package model.interfaces;

import java.util.List;

import model.City;
import model.Genome;
import model.Individual;
import model.Individual.Gender;
import model.Language;
import model.World;
import model.Zone;

public interface IAIEngine {

	/*----------WORLD------------------------------------*/

	public abstract Zone getRandomZone(World world);

	public abstract Zone getRandomZonePopWeighted(World world);

	public abstract Zone getRandomZoneSizeWeighted(World world);

	public abstract int getNCitizens(World world);

	public abstract Individual getRandomCitizen(World world);

	public abstract List<Individual> getRandomCitizens(World world, int n);

	public abstract void updateZones(World world);

	public abstract void updateIndividuals(World world);

	/*----------ZONE-------------------------------------*/

	public abstract City getRandomCity(Zone zone);

	public abstract City getRandomCityPopWeighted(Zone zone);

	public abstract int getNCitizens(Zone zone);

	public abstract Individual getRandomCitizen(Zone zone);

	public abstract List<Individual> getRandomCitizens(Zone zone, int n);

	public abstract List<City> getAdjacentCities(Zone zone);

	public abstract List<Zone> getAdjacentZones(Zone zone);

	public abstract boolean isCityAdjacent(Zone zone, City adj);

	public abstract boolean isZoneAdjacent(Zone zone, Zone adj);

	public abstract void updateIndividuals(Zone zone, float moment);

	/*----------LANG-------------------------------------*/

	public abstract String getRandomName(Language language, Gender gender);

	public abstract String getRandomSurname(Language language);

	/*----------CITY-------------------------------------*/

	public abstract Individual getRandomCitizen(City city);

	public abstract List<Individual> getRandomCitizens(City city, int n);

	public abstract void changeParentZone(City city, Zone nZone);

	public abstract void procIndividualDeath(City city, Individual individual, float deathDate);

	/*----------INDIVIDUAL-------------------------------*/

	public abstract String getCurrentCityFromParents(Individual father, Individual mother);

	public abstract String[] getNameSurnamesFromParents(Gender gender, Individual father, Individual mother);

	public abstract float getRepInheritedFromParents(Individual father, Individual mother);

	public abstract void pairIndividuals(Individual indA, Individual indB);

	public abstract void divorce(Individual indA, Individual indB);

	public abstract void propagateReputation(Individual individual, float val);
	
	public abstract boolean[] agreeToPair(Individual indA, Individual indB, float moment);
	
	public abstract List<City> getAvailableMigrationTargets(Individual individual);

	public abstract void killIndividual(Individual individual, float deathDate);
	
	public abstract void migrateTo(Individual individual, City nucity);

	public abstract float getDesirability(Individual individual);

	public abstract float getGenderAttraction(Individual individual, Gender target);

	public abstract List<Individual> getAncestors(Individual individual, int lvl);

	public abstract List<Individual> getDescendants(Individual individual, int lvl);

	public abstract List<Individual> getRelatives(Individual individual, int lvl);

	/*----------GENOME-----------------------------------*/

	public abstract Gender getGender(Genome genome);

	public abstract byte getSexOrientation(Genome genome);

	public abstract short getStrength(Genome genome);

	public abstract short getConstitution(Genome genome);

	public abstract short getSpeed(Genome genome);

	public abstract short getIntelligence(Genome genome);

	public abstract short getWisdom(Genome genome);

	public abstract short getCharisma(Genome genome);

	public abstract short getBeauty(Genome genome);

	public abstract short getFertility(Genome genome);

	public abstract short getHorniness(Genome genome);

	public abstract short getComformity(Genome genome);

	public abstract double getChromosomeDominance(int chromosome, int gene);

	/*----------META-------------------------------------*/

	public abstract void finish();

}
