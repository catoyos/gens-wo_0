package model.interfaces;

import java.util.List;

import model.City;
import model.Individual;
import model.World;
import model.Zone;

public interface IAIEngine {

	public abstract Zone getRandomZone(World world);

	public abstract Zone getRandomZonePopWeighted(World world);

	public abstract Zone getRandomZoneSizeWeighted(World world);

	public abstract int getNCitizens(World world);

	public abstract Individual getRandomCitizen(World world);

	public abstract List<Individual> getRandomCitizens(World world, int n);

	public abstract void updateZones(World world);

	public abstract void updateIndividuals(World world);

/*---------------------------------------------------*/
	
	public abstract City getRandomCity(Zone zone);

	public abstract City getRandomCityPopWeighted(Zone zone);

	public abstract int getNCitizens(Zone zone);

	public abstract Individual getRandomCitizen(Zone zone);

	public abstract List<Individual> getRandomCitizens(Zone zone, int n);

	public abstract void updateCitizens(Zone zone, float moment);

/*---------------------------------------------------*/
	public abstract void finish();
/*---------------------------------------------------*/

}
