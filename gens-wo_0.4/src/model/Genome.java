package model;

import java.util.Random;

public class Genome {
	private static final int GENES_PER_CHR = 4;
	private static final int GENBASE = 16;
	private static final int[] EXP_GENBASE = {1, 16, 256, 4096, 65536, 1048576};
	private static final Random RND = new Random();
	
	private short[][] genes;

	public short[][] getGenes() {
		return genes;
	}

	public void setGenes(short[][] genes) {
		this.genes = genes;
	}
	
}
