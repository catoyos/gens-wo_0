package ai_engine;

import model.Genome;
import model.Individual.Gender;

public class MyAIEngineGenome {

	public static Gender genomeGetGender(Genome genome) {
		return genome.getGenValue(0, 0) < 0 ? Gender.FEMALE : Gender.MALE;
	}

	public static byte genomeGetSexOrientation(Genome genome) {
		short gv = genome.getGenValue(1, 0);
		return (byte) (64 - 128 * gv * gv * gv / 3375);
	}

	public static short genomeGetStrength(Genome genome) {
		return (short) (genome.getGenValue(1, 3)
				+ genome.getGenValue(2, 2)
				+ (15 - genome.getGenValue(4, 1)) * 2
				+ genome.getGenValue(5, 1) * 3);
	}

	public static short genomeGetConstitution(Genome genome) {
		return (short) (genome.getGenValue(2, 3)
				+ genome.getGenValue(3, 2)
				+ (15 - genome.getGenValue(5, 1)) * 2
				+ genome.getGenValue(6, 1) * 3);
	}

	public static short genomeGetSpeed(Genome genome) {
		return (short) (genome.getGenValue(3, 3)
				+ genome.getGenValue(4, 2)
				+ (15 - genome.getGenValue(6, 1)) * 2
				+ genome.getGenValue(7, 1) * 3);
	}

	public static short genomeGetIntelligence(Genome genome) {
		return (short) (genome.getGenValue(4, 3)
				+ genome.getGenValue(5, 2)
				+ (15 - genome.getGenValue(7, 1)) * 2
				+ genome.getGenValue(8, 1) * 3);
	}

	public static short genomeGetWisdom(Genome genome) {
		return (short) (genome.getGenValue(5, 3)
				+ genome.getGenValue(6, 2)
				+ (15 - genome.getGenValue(8, 1)) * 2
				+ genome.getGenValue(9, 1) * 3);
	}

	public static short genomeGetCharisma(Genome genome) {
		return (short) (genome.getGenValue(6, 3)
				+ genome.getGenValue(7, 2)
				+ (15 - genome.getGenValue(9, 1)) * 2
				+ genome.getGenValue(1, 1) * 3);
	}

	public static short genomeGetBeauty(Genome genome) {
		return (short) (genome.getGenValue(7, 3)
				+ genome.getGenValue(8, 2)
				+ (15 - genome.getGenValue(1, 1)) * 2
				+ genome.getGenValue(2, 1) * 3);
	}

	public static short genomeGetFertility(Genome genome) {
		return (short) (genome.getGenValue(8, 3)
				+ genome.getGenValue(9, 2)
				+ (15 - genome.getGenValue(2, 1)) * 2
				+ genome.getGenValue(3, 1) * 3);
	}

	public static short genomeGetHorniness(Genome genome) {
		return (short) (genome.getGenValue(9, 3)
				+ genome.getGenValue(1, 2)
				+ (15 - genome.getGenValue(3, 1)) * 2
				+ genome.getGenValue(4, 1) * 3);
	}
	
	public static int genomeComputeChromosomeValue(Genome genome, int n, int chromosomeA, int chromosomeB) {
		return chromosomeA;
	}

}
