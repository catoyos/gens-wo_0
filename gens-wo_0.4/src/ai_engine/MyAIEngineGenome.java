package ai_engine;

import model.Genome;
import model.Individual.Gender;

public class MyAIEngineGenome {

	private static final double[] props = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	
	public static Gender getGender(Genome genome) {
		return genome.getGenValue(0, 0) < 7 ? Gender.FEMALE : Gender.MALE;
	}

	public static byte getSexOrientation(Genome genome) {
		short gv = genome.getGenValue(1, 0);
//		return (byte) (64 - 128 * gv * gv * gv / 3375);
		return (byte) (32 - 64 * gv * gv * gv / 3375);
	}

	public static short getStrength(Genome genome) {
		return (short) (genome.getGenValue(1, 1) + (15 - genome.getGenValue(2, 2)) + genome.getGenValue(4, 3) * 2 + genome.getGenValue(6, 2) * 3);
	}

	public static short getConstitution(Genome genome) {
		return  (short) (genome.getGenValue(2, 1) + (15 - genome.getGenValue(3, 2)) + genome.getGenValue(4, 3) * 2 + genome.getGenValue(7, 2) * 3);
	}
	
	public static short getSpeed(Genome genome) {
		return (short) (genome.getGenValue(3, 1) + (15 - genome.getGenValue(4, 2)) + genome.getGenValue(5, 3) * 2 + genome.getGenValue(8, 2) * 3);
	}
	
	public static short getIntelligence(Genome genome) {
		return (short) (genome.getGenValue(4, 1) + (15 - genome.getGenValue(5, 2)) + genome.getGenValue(5, 3) * 2 + genome.getGenValue(9, 2) * 3);
	}
	
	public static short getWisdom(Genome genome) {
		return (short) (genome.getGenValue(5, 1) + (15 - genome.getGenValue(6, 2)) + genome.getGenValue(7, 3) * 2 + genome.getGenValue(10, 2) * 3);
	}
	
	public static short getCharisma(Genome genome) {
		return (short) (genome.getGenValue(6, 1) + (15 - genome.getGenValue(7, 2)) + genome.getGenValue(7, 3) * 2 + genome.getGenValue(1, 2) * 3);
	}
	
	public static short getBeauty(Genome genome) {
		return (short) (genome.getGenValue(7, 1) + (15 - genome.getGenValue(8, 2)) + genome.getGenValue(6, 3) * 2 + genome.getGenValue(2, 2) * 3);
	}
	
	public static short getFertility(Genome genome) {
		return (short) (genome.getGenValue(8, 1) + (15 - genome.getGenValue(9, 2)) + genome.getGenValue(6, 3) * 2 + genome.getGenValue(3, 2) * 3);
	}
	
	public static short getHorniness(Genome genome) {
		return (short) (genome.getGenValue(9, 1) + (15 - genome.getGenValue(10, 2)) + genome.getGenValue(8, 3) * 2 + genome.getGenValue(4, 2) * 3);
	}
	
	public static short getComformity(Genome genome) {
		return (short) (genome.getGenValue(10, 1) + (15 - genome.getGenValue(1, 2)) + genome.getGenValue(8, 3) * 2 + genome.getGenValue(5, 2) * 3);
	}

	public static double getChromosomeDominance(int chromosome) {
		return (chromosome >= 0 && chromosome < 23) ? props[chromosome] : 0.5;
	}
}
