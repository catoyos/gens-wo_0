package main_logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import main_logic.io.IOMenu;
import main_logic.io.IOStrings;
import main_logic.io.InputOutput;
import model.interfaces.IAIEngine;
import ai_engine.MyAIEngine;

public class MainPLD4 {
	private Properties properties;
	private Universe uni;
	private Player player;

	public MainPLD4() {
//		this(InputOutput.DEFAULT_ROOT_FOLDER + File.separator + "config.properties");
		this("config.properties");
	}
	
	public MainPLD4(String configfile) {
		this.properties = new Properties();
		try {
			properties.load(new FileInputStream(configfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!properties.containsKey("folder")) {
			properties.setProperty("folder", "files");
		}
	}
	
	public void run() {
		this.uni = loadUniverse();
		System.out.println(uni);//----------------DEBUG
		selectWorld();
		this.player = selectPlayer();
		
		boolean play;
		if (player.isSet() && !player.isOver()) play = true;
		else play = false;
		
		boolean watch;
		if (play) watch = true;
		else watch = keepWatching();
		
		int i = 0;
		int laps = 10;
		int timelength = laps;
		int sublap = 4;
		double lapse = 1.0 / sublap;

		while ((play || watch) && (i++ < timelength)) {
			//System.out.println(uni.getCurrentWorldMoment());// --------------DEBUG-----------
			for (int j = 0; j < sublap; j++) {
				jumpMoment(lapse);
				updateZones();
				updateIndividuals();
				uni.spreadWealth(lapse);
			}
			
			if (play) {
				if (player.isSet() && !player.isOver()) {
					updatePlayer();
				} else {
					play = restartPlayer();
					if (!play) watch = keepWatching();
				}
			}

			if (i >= timelength) {
				i = 0;
				timelength = addPlayLaps();
			}
		}
		finish();

		System.out.println(uni);//----------------DEBUG
	}

	private void finish() {
		uni.finish();
		player.finish();
	}

	private int addPlayLaps() {
		InputOutput.println(IOStrings.Q_CONT_CURRENT_WORLD + IOStrings.OPT_Y_N);
		if (InputOutput.readboolean(IOStrings.OP_Y_CHAR)) {
			InputOutput.println(IOStrings.Q_YEARS_TO_CONT);
			int nn = (int) (InputOutput.readnumber());
			if (nn < 0) {
				return 0;
			} else {
				return nn;
			}
		} else {
			return 0;
		}
	}

	private boolean keepWatching() {
		InputOutput.println(IOStrings.Q_CONT_AS_WATCHER + IOStrings.OPT_Y_N);
		return InputOutput.readboolean(IOStrings.OP_Y_CHAR);
	}

	private boolean restartPlayer() {
		InputOutput.println(IOStrings.Q_CHOSE_NEW_PLAYER + IOStrings.OPT_Y_N);
		if (InputOutput.readboolean(IOStrings.OP_Y_CHAR)) {
			this.player = selectPlayer();
			return true;
		} else {
			return false;
		}
	}

	private void updatePlayer() {
		player.update();
		
	}

	private void updateIndividuals() {
		uni.updateCurrentWorldIndividuals();
	}

	private void updateZones() {
		uni.updateCurrentWorldZones();
	}

	private void jumpMoment(double lapse) {
		uni.modifyCurrentWorldMoment((float) lapse);
	}

	private Player selectPlayer() {
		Player res = new Player();
		res.setIndividual(uni.getRandomCurrentCitizen());//TODO
		return res;
	}

	private Universe loadUniverse() {
		Universe res = null;
		IAIEngine aieng = loadAIEngine();
		try {
			if (properties.getProperty("universe") != null) {
				String rootfolder = properties.getProperty("folder");
				File file = Universe.getUniverseFile(properties.getProperty("universe"), rootfolder);
				res = Universe.generateFromString(InputOutput.getFileContent(file), aieng, rootfolder);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (res == null) {
			res = new Universe(aieng);
			properties.setProperty("universe", res.uniID);
		}
		
		return res;
	}

	private IAIEngine loadAIEngine() {
		String aiestr = properties.getProperty("aieng");
		IAIEngine aieng = null;
		if (aiestr != null) {
			try {
				aieng = (IAIEngine) Class.forName(aiestr).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (aieng == null) {
			aieng = new MyAIEngine();
			properties.setProperty("aieng", aieng.getClass().getName());
		}
		
		return aieng;
	}

	private void selectWorld() {
		boolean fin = false;
		while (!fin) {
			IOMenu iom = new IOMenu(IOStrings.MENU_SELECT_WORLD_TITLE);
			if (uni.isCurrentSet()) {
				iom.addOption("0", IOStrings.MENU_SELECT_WORLD_OPT_CONT, 0);
			}
			iom.addOption("1", IOStrings.MENU_SELECT_WORLD_OPT_CREATE, 1);
			if (uni.hasWorlds()) {
				iom.addOption("2", IOStrings.MENU_SELECT_WORLD_OPT_LOAD, 2);
			}
			
			
			int opc = InputOutput.askForOption(iom);
			switch (opc) {
			case 0:
				fin = continueWorld();
				break;
			case 1:
				fin = createWorld();
				break;
			case 2:
				fin = loadExistingWorld();
				break;

			default: // TODO
				break;
			}
		}
	}

	private boolean continueWorld() {// TODO
		InputOutput.println("continueWorld");// --------------DEBUG-----------
		return uni.isCurrentSet();
	}

	private boolean createWorld() {// TODO
		InputOutput.println("createWorld");// --------------DEBUG-----------
		String wid = uni.generateNewWorld();
		uni.setCurrentWorld(uni.getWorld(wid));
		return uni.isCurrentSet();
	}

	private boolean loadExistingWorld() {// TODO
		InputOutput.println("loadExistingWorld");// --------------DEBUG-----------
		if (uni.hasWorlds()) {
			IOMenu iom = new IOMenu(IOStrings.MENU_SELECT_WORLD);
			iom.addOption(Integer.toString(0), IOStrings.MENU_RETURN, 0);
			int i = 1;
			for (String world : uni.getWorldIDs()) {
				iom.addOption(Integer.toString(i), uni.getWorldInfoLine(world), i);
				i++;
			}

			int optm = InputOutput.askForOption(iom);
			if (optm == 0) {
				return false;
			} else {
				uni.setCurrentWorld(uni.getWorld(uni.getWorldIDs().get(optm - 1)));
			}
		} else {
			InputOutput.println("ningún mundo. creando nuevo.");// --------------DEBUG-----------
			createWorld();
		}
		return uni.isCurrentSet();
	}

	public static void main(String[] args) {
		String configfile = "config.properties";
//		String configfile = InputOutput.DEFAULT_ROOT_FOLDER + File.separator + "config.properties";
		MainPLD4 m = new MainPLD4(configfile);
		m.run();
		try {
			m.properties.store(new FileOutputStream(configfile), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
