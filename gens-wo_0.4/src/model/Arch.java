package model;

import java.util.Random;

import model.interfaces.IAIEngine;
import model.interfaces.IStorage;

public final class Arch {
	protected static final Random RND = new Random();
	private static IStorage sto;
	private static IAIEngine aie;
		
	private Arch() throws Exception {
		throw new Exception("What are you doing tryin' to instantiate this? Stahp it pls.");
	}
	
	public static void setStorage(IStorage storage){
		Arch.sto = storage;
	}
	
	public static void setAIEngine(IAIEngine aiengine){
		Arch.aie = aiengine;
	}

	public static World getClearWorld() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearWorld();
		}
	}

	public static Zone getClearZone() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearZone();
		}
	}

	public static Language getClearLanguage() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearLanguage();
		}
	}

	public static City getClearCity() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearCity();
		}
	}

	public static Individual getClearIndividual() {
		if (sto == null) {
			System.out.println("??");
			return null;
		} else {
			return sto.getClearIndividual();
		}
	}

	public void finish() {
		sto.finish();
		aie.finish();
	}
	
	
}
