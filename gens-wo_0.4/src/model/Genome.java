package model;


public class Genome {
	private static final int GENES_PER_CHR = 4;
	private static final int GENBASE = 16;
	private static final int[] EXP_GENBASE = {1, 16, 256, 4096, 65536, 1048576};
	
	private short[][] genes;

	public short[][] getGenes() {
		return genes;
	}
	
	public static Genome generateFromString(Genome res, String str) {
		if(res == null) res = new Genome();
		//TODO
		return res;
	}

	public void setGenes(short[][] genes) {
		this.genes = genes;
	}

	public short getGenValue(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
