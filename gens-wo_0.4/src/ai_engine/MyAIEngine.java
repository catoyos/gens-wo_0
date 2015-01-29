package ai_engine;

import java.util.List;
import java.util.Random;

import model.City;
import model.Individual;
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
	public void finish() {
		// TODO Auto-generated method stub
		
	}




}
