package model;

import java.util.Random;

import model.interfaces.IAIEngine;
import model.interfaces.IStorage;

public class Arch {
	protected static final Random RND = new Random();
	protected static IStorage sto;
	protected static IAIEngine aie;
	
	public Arch(IStorage storage, IAIEngine aiengine) {
		Arch.sto = storage;
		Arch.aie = aiengine;
	}

	public void finish() {
		sto.finish();
		aie.finish();
	}
	
	
}
