package model;

import model.Individual.Gender;

public class Genome {
	private static final String STRAND_SEP = "#".intern();
	private static final String GEN_SEP = "·".intern();
	private static final String GENOME_ID = "G".intern();
	
	private static final int GENES_PER_CHR = 4;
	private static final int GENBASE = 16;
	private static final int[] EXP_GENBASE = {1, 16, 256, 4096, 65536, 1048576};
	
	private static final int ngs = EXP_GENBASE[GENES_PER_CHR];
	
	private short[][] genes;

	public Genome() {
		this.genes = new short[2][23];
	}
	
	public static void generateNewGenome(Genome res) {
//		res.genes = new short[2][23];
		res.genes[0][0] = (short) Arch.RND.nextInt(ngs);
		res.genes[1][0] = (short) Arch.RND.nextInt(ngs);		
		
		for (int i = 1; i < res.genes[0].length; i++) {
			res.genes[0][i] = (short) ((Arch.RND.nextInt(ngs) + Arch.RND.nextInt(ngs))/2);
			res.genes[1][i] = (short) ((Arch.RND.nextInt(ngs) + Arch.RND.nextInt(ngs))/2);
		}
		
		res.modifyGenValue(1, 0, 0, (byte) 8);
	}

	public static void generateFromString(Genome res, String string) {
		res.genes = new short[2][23];
		String[] strands = string.split(STRAND_SEP);
		String[] strgen;
		for (int a = 0; a < 2; a++) {
			strgen = strands[a + 1].split(GEN_SEP);
			for (int i = 0; i < res.genes[0].length; i++) {
				res.genes[a][i] = (short) (Integer.parseInt(strgen[i], 36));
			}
		}
	}

	public static void generateGenomeFromParents(Genome res, Genome gnFather, Genome gnMother) {
		res.genes = new short[2][23];
		for (int i = 0; i < res.genes[0].length; i++) {
			res.genes[0][i] = Arch.RND.nextBoolean() ? gnFather.genes[0][i] : gnFather.genes[1][i];
			res.genes[1][i] = Arch.RND.nextBoolean() ? gnMother.genes[0][i] : gnMother.genes[1][i];
		}
	}

	public void clear() {
		this.genes = new short[2][23];
	}
	
	public short[][] getGenes() {//TODO restringir acceso
		return genes;
	}

	public void setGenes(short[][] genes) {
		this.genes = genes;
	}

	public byte getGenValue(int chromosome, int gene) {
		byte[] val = getGenValues(chromosome, gene);
		int[] res = new int[2];
		if (val[0] > val[1]) {
			res[0] = val[0];
			res[1] = val[1];
		} else {
			res[0] = val[1];
			res[1] = val[0];
		}
		double fxt = getChromosomeDominance(chromosome, gene);
		return (byte) (fxt * res[0] + (1 - fxt) * res[1]);
	}

	public byte[] getGenValues(int chromosome, int gene) {
		byte[] res = new byte[2];
		
		int val = signedToUnsigned(genes[0][chromosome]);
		res[0] = (byte) ((val / EXP_GENBASE[gene]) - GENBASE * (val / EXP_GENBASE[gene + 1]));
		
		val = signedToUnsigned(genes[1][chromosome]);
		res[1] = (byte) ((val / EXP_GENBASE[gene]) - GENBASE * (val / EXP_GENBASE[gene + 1]));
		
		return res;
	}

	public void modifyGenValue(int chromosomeA, int chromosomeB, int gene, byte amount){
		byte[] vals = new byte[GENES_PER_CHR];
		vals[gene] = amount;
		int v = signedToUnsigned(genes[chromosomeA][chromosomeB]);
		short res = 0;
		
		for (int i = 0; i < vals.length; i++) {
			vals[i] += (v / EXP_GENBASE[i]) - GENBASE * (v / EXP_GENBASE[i + 1]);
			res += Math.max(0, Math.min(GENBASE - 1, vals[i])) * EXP_GENBASE[i];
		}
		
		genes[chromosomeA][chromosomeB] = res;
	}
	
	private static int signedToUnsigned(short n){
		return (65536 + n) % 65536;
	}

	/*------------------------------------------------*/
	
	public Gender getGender() {
		return Arch.aie.getGender(this);
	}

	public byte getSexOrientation() {
		return Arch.aie.getSexOrientation(this);
	}

	public short getStrength() {
		return Arch.aie.getStrength(this);
	}
	
	public short getConstitution() {
		return Arch.aie.getConstitution(this);
	}

	public short getSpeed() {
		return Arch.aie.getSpeed(this);
	}

	public short getIntelligence() {
		return Arch.aie.getIntelligence(this);
	}

	public short getWisdom() {
		return Arch.aie.getWisdom(this);
	}

	public short getCharisma() {
		return Arch.aie.getCharisma(this);
	}

	public short getBeauty() {
		return Arch.aie.getBeauty(this);
	}

	public short getFertility() {
		return Arch.aie.getFertility(this);
	}

	public short getHorniness() {
		return Arch.aie.getHorniness(this);
	}

	public short getComformity() {
		return Arch.aie.getComformity(this);
	}

	private static double getChromosomeDominance(int chromosome, int gene) {
		return Arch.aie == null ? 0.5 : Arch.aie.getChromosomeDominance(chromosome, gene);
	}
	
	/*------------------------------------------------*/

	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(GENOME_ID);
		if (genes != null) {
			for (int j = 0; j < genes.length; j++) {
				res.append(STRAND_SEP);
				res.append(Integer.toString(signedToUnsigned(genes[j][0]),36));
				for (int i = 1; i < genes[0].length; i++) {
					res.append(GEN_SEP).append(Integer.toString(signedToUnsigned(genes[j][i]),36));
				}
			}
		}
		return res.toString();
	}
	
}
