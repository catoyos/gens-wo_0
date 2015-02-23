package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.utils.StringUtils;

public class Individual extends Storable {

	private static final String FILESTRING_SEP = ",".intern();
	private static final String LIST_SEP = "@".intern();
	private static final String EMPTY_ID = "".intern();
	private static final String FSTR_GM = "*".intern();
	private static final String FSTR_GF = "+".intern();
	
	
	public static enum Gender { MALE, FEMALE }
	public static enum HairColor { WHITE, PLATINUM, BLONDE, LITEBROWN, RED, BROWN, AUBURN, BLACK }
	public static enum EyeColor { GRAY, BLUE, GREEN, HAZEL, BROWN, BLACK }

	protected static long newIDn = 1l;

	private String individualID;
	private String name;
	private String surnameA;
	private String surnameB;
	private Gender gender;
	private float birthDate;
	private float deathDate;
	private String originalZoneID;
	private String currentCityID;
	private Genome genome;
	private String fatherID;
	private String motherID;
	private String partnerID;
	private List<String> childrenID;
	private List<String> inventory;
	private float rep;

	private byte sexOrientation;
	private short strength;
	private short constitution;
	private short speed;
	private short intelligence;
	private short wisdom;
	private short charisma;
	private short beauty;
	private short fertility;
	private short horniness;
	private short comformity;
	
	public Individual() {
		super(StorableType.INDIVIDUAL);
		this.individualID = EMPTY_ID;
		this.genome = new Genome();
		this.childrenID = new LinkedList<String>();
		this.inventory = new LinkedList<String>();
	}
	
	public static void generateNewIndividual(Individual res, String cityID, float birthDate) {
		Zone oZone = null;
		if(Arch.cityExists(cityID))
			oZone = Arch.getCityById(cityID).getParentZone();
		
		if(oZone == null) {
			res.originalZoneID="XXXXXXXXX".intern();//TODO controlar aqui esto
		} else {
			res.originalZoneID = oZone.getZoneID();
			res.surnameA = oZone.getRandomSurname();
			res.surnameB = oZone.getRandomSurname();
		}
		
		res.individualID = StringUtils.generateIndividualId(newIDn++, res.originalZoneID.substring( res.originalZoneID.length() - 2, res.originalZoneID.length()));
		res.birthDate = birthDate;
		res.deathDate = -1f;
		res.currentCityID = cityID;
		res.fatherID = null;
		res.motherID = null;
		res.partnerID = null;
		res.rep = 0;

		if (res.childrenID == null) res.childrenID = new LinkedList<String>();
		else res.childrenID.clear();
			
		if (res.inventory == null) res.inventory = new LinkedList<String>();
		else res.inventory.clear();
		
		if (res.genome == null) res.genome = new Genome();		
		Genome.generateNewGenome(res.genome);

		loadDataFromGenome(res);
		
		if(oZone != null) res.name = oZone.getRandomName(res.gender).intern();
		
	}

	public static void generateFromString(Individual res, String string) {
		String[] data = string.split(FILESTRING_SEP);
		res.individualID = data[0].intern();
		res.name = data[1].intern();
		res.surnameA = data[2].intern();
		res.surnameB = data[3].intern();
		res.gender = data[4].equals(FSTR_GM) ? Gender.MALE : Gender.FEMALE;
		try {
			res.birthDate = StringUtils.df.parse(data[5]).floatValue();
		} catch (Exception e) {
			e.printStackTrace();
			res.birthDate = 0;
		}
		try {
			res.deathDate = StringUtils.df.parse(data[6]).floatValue();
		} catch (Exception e) {
			e.printStackTrace();
			res.deathDate = res.birthDate;
		}
		res.originalZoneID = data[7].intern();
		res.currentCityID = data[8].intern();
		res.fatherID = data[9].equals(EMPTY_ID) ? null : data[9].intern();
		res.motherID = data[10].equals(EMPTY_ID) ? null : data[10].intern();
		res.partnerID = data[11].equals(EMPTY_ID) ? null : data[11].intern();

		try {
			res.rep = StringUtils.df.parse(data[12]).floatValue();
		} catch (Exception e) {
			e.printStackTrace();
			res.rep = 0;
		}
		res.childrenID = new LinkedList<String>(StringUtils.compactStringToList(data[13], LIST_SEP));
		res.inventory = new LinkedList<String>(StringUtils.compactStringToList(data[14], LIST_SEP));

		try {
			if (res.genome == null) res.genome = new Genome();
			Genome.generateFromString(res.genome, data[15]);
		} catch (Exception e) {
			Genome.generateNewGenome(res.genome);
			e.printStackTrace();
		}
		
		loadDataFromGenome(res);

	}

	public static void generateIndividualFromParents(Individual res, Individual father, Individual mother, float birthDate) {
		res.currentCityID = getCurrentCityFromParents(father, mother);
		if(Arch.cityExists(res.currentCityID))
			res.originalZoneID = Arch.getCityById(res.currentCityID).getParentZoneID();
		
		if(res.originalZoneID == null) {
			res.originalZoneID = "XXXXXXXXX".intern();
		}
		
		if (res.genome == null) res.genome = new Genome();
		Genome.generateGenomeFromParents(res.genome, father.genome, mother.genome);

		loadDataFromGenome(res);

		res.individualID = StringUtils.generateIndividualId(newIDn++, res.originalZoneID.substring(res.originalZoneID.length() - 2, res.originalZoneID.length()));

		String[] nameSurnames = getNameSurnamesFromParents(res.gender, father, mother);
		res.name = nameSurnames[0];
		res.surnameA = nameSurnames[1];
		res.surnameB = nameSurnames[2];
		res.birthDate = birthDate;
		res.deathDate = -1f;
		res.fatherID = father.individualID;
		res.motherID = mother.individualID;
		res.partnerID = null;
		res.rep = getRepInheritedFromParents(father, mother);

		if (res.childrenID == null) res.childrenID = new LinkedList<String>();
		else res.childrenID.clear();
			
		if (res.inventory == null) res.inventory = new LinkedList<String>();
		else res.inventory.clear();

	}
	
	private static void loadDataFromGenome(Individual indi) {
		indi.gender = indi.genome.getGender();
		indi.sexOrientation = indi.genome.getSexOrientation();
		indi.strength = indi.genome.getStrength();
		indi.constitution = indi.genome.getConstitution();
		indi.speed = indi.genome.getSpeed();
		indi.intelligence = indi.genome.getIntelligence();
		indi.wisdom = indi.genome.getWisdom();
		indi.charisma = indi.genome.getCharisma();
		indi.beauty = indi.genome.getBeauty();
		indi.fertility = indi.genome.getFertility();
		indi.horniness = indi.genome.getHorniness();
		indi.comformity = indi.genome.getComformity();
	}

	public void clear() {
		super.modified = false;
		this.individualID = EMPTY_ID;
		this.name = null;
		this.surnameA = null;
		this.surnameB = null;
		
		this.gender = null;
		
		this.birthDate = 0;
		this.deathDate = 0;
		this.originalZoneID = null;
		this.currentCityID = null;
		if(genome == null) this.genome = new Genome();
		else this.genome.clear();
		
		this.fatherID = null;
		this.motherID = null;
		this.partnerID = null;
		if(childrenID == null) this.childrenID = new LinkedList<String>();
		else this.childrenID.clear();

		if(inventory == null) this.inventory = new LinkedList<String>();
		else this.inventory.clear();
		
		this.rep = 0;
		this.sexOrientation = 0;
		this.strength = 0;
		this.constitution = 0;
		this.speed = 0;
		this.intelligence = 0;
		this.wisdom = 0;
		this.charisma = 0;
		this.beauty = 0;
		this.fertility = 0;
		this.horniness = 0;
		this.comformity = 0;
	}
	
	public String getIndividualID() {
		return individualID;
	}
//	public void setIndividualID(String individualID) {
//		this.individualID = individualID;
//	}
	public boolean isID(String oID) {
		return oID != null && this.individualID.equals(oID) && !this.individualID.equals(EMPTY_ID);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		super.modified = true;
		this.name = name;
	}
	public String getSurnameA() {
		return surnameA;
	}
	public void setSurnameA(String surnameA) {
		super.modified = true;
		this.surnameA = surnameA;
	}
	public String getSurnameB() {
		return surnameB;
	}
	public void setSurnameB(String surnameB) {
		super.modified = true;
		this.surnameB = surnameB;
	}
	public String getNameSurnames() {
		return name + " " + surnameA + " " + surnameB;
	}
	public float getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(float birthDate) {
		super.modified = true;
		this.birthDate = birthDate;
	}
	public float getAge(float moment) {
		return moment - birthDate;
	}
	public float getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(float deathDate) {
		super.modified = true;
		this.deathDate = deathDate;
	}
	public boolean isAlive() {
//		return deathDate != -1f;
		return deathDate < 0f;
	}
	public String getOriginalZoneID() {
		return originalZoneID;
	}
	public void setOriginalZoneID(String originalZoneID) {
		super.modified = true;
		this.originalZoneID = originalZoneID;
	}
	public String getCurrentCityID() {
		return currentCityID;
	}
	public void setCurrentCityID(String currentCityID) {
		super.modified = true;
		this.currentCityID = currentCityID;
	}

	public String getFatherID() {
		return fatherID;
	}
	public void setFatherID(String fatherID) {
		super.modified = true;
		this.fatherID = fatherID;
	}
	public String getMotherID() {
		return motherID;
	}
	public void setMotherID(String motherID) {
		super.modified = true;
		this.motherID = motherID;
	}
	public boolean isChildOf(String pID) {
		return pID != null && (pID.equals(motherID) || pID.equals(fatherID));
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		super.modified = true;
		this.partnerID = partnerID;
	}
	public boolean hasPartner(){
		return partnerID != null;
	}
	public List<String> getChildrenID() {
		return Collections.unmodifiableList(childrenID);
	}
	public void setChildrenID(List<String> childrenID) {
		super.modified = true;
		this.childrenID = childrenID;
	}
	
	public void addChild(String sChildID) {
		super.modified = true;
		childrenID.add(sChildID);
	}

	public void addChild(Individual zChild) {
		super.modified = true;
		childrenID.add(zChild.individualID);
	}

	public boolean containsChild(String sChildID) {
		return childrenID.contains(sChildID);
	}

	public boolean containsChild(Individual zChild) {
		return childrenID.contains(zChild.individualID);
	}

	public boolean isEmptyChildren() {
		return childrenID.isEmpty();
	}

	public boolean removeChild(String sChildID) {
		super.modified = true;
		return childrenID.remove(sChildID);
	}

	public boolean removeChild(Individual zChild) {
		super.modified = true;
		return childrenID.remove(zChild.individualID);
	}

	public int getNChildren() {
		return childrenID.size();
	}
	
	public List<String> getInventory() {
		return Collections.unmodifiableList(inventory);
	}
	public void setInventory(List<String> inventory) {
		super.modified = true;
		this.inventory = inventory;
	}
	public float getRep() {
		return rep;
	}
	public void setRep(float rep) {
		super.modified = true;
		this.rep = rep;
	}
	public void modifyReputation(float val) {
		super.modified = true;
		rep += val;
	}
	public Gender getGender() {
		return gender;
	}
	public byte getSexOrientation() {
		return sexOrientation;
	}
	public short getStrength() {
		return strength;
	}
	public short getConstitution() {
		return constitution;
	}
	public short getSpeed() {
		return speed;
	}
	public short getIntelligence() {
		return intelligence;
	}
	public short getWisdom() {
		return wisdom;
	}
	public short getCharisma() {
		return charisma;
	}
	public short getBeauty() {
		return beauty;
	}
	public short getFertility() {
		return fertility;
	}
	public short getHorniness() {
		return horniness;
	}
	public short getComformity() {
		return comformity;
	}

	public short[] getCharactValues() {
		short[] res = new short[10];
		res[0] = strength;
		res[1] = constitution;
		res[2] = speed;
		res[3] = intelligence;
		res[4] = wisdom;
		res[5] = charisma;
		res[6] = beauty;
		res[7] = fertility;
		res[8] = horniness;
		res[9] = comformity;
		return res;
	}

	/*------------------------------------------------*/
	
	public Individual[] getParents(){
		if (Arch.sto == null) return null; else{
			Individual[]res=new Individual[2];
			res[0] = Arch.sto.getIndividualById(fatherID);
			res[1] = Arch.sto.getIndividualById(motherID);
			return res;
		}
	}
	public Individual getPartner(){
		return Arch.sto == null ? null : Arch.sto.getIndividualById(partnerID);
	}
	public List<Individual> getChildren(){
		return Arch.sto == null ? null : Arch.sto.getIndividualsById(childrenID);
	}
	public Zone getOriginalZone(){
		return Arch.sto == null ? null : Arch.sto.getZoneById(originalZoneID);
	}
//	public Language getLanguage(){
//		Zone oz = getOriginalZone();
//		return oz == null ? null : oz.getLang();
//	}
	public City getCurrentCity(){
		return Arch.sto == null ? null : Arch.sto.getCityById(currentCityID);
	}
	public Zone getCurrentZone(){
		if (!Arch.sto.cityExists(currentCityID)) return null;
		return Arch.sto == null ? null : Arch.sto.getCityById(currentCityID).getParentZone();
	}
	public boolean isInOriginalZone(){
		if (!Arch.sto.cityExists(currentCityID)) return false;
		return Arch.sto == null ? false : Arch.sto.getCityById(currentCityID).getParentZoneID().equals(this.originalZoneID);
	}
	

	private static String getCurrentCityFromParents(Individual father, Individual mother) {
		return Arch.aie == null ? null : Arch.aie.getCurrentCityFromParents(father, mother);
	}
	
	private static String[] getNameSurnamesFromParents(Gender gender, Individual father, Individual mother) {
		return Arch.aie == null ? null : Arch.aie.getNameSurnamesFromParents(gender, father, mother);
	}
	
	private static float getRepInheritedFromParents(Individual father, Individual mother) {
		return Arch.aie == null ? 0 : Arch.aie.getRepInheritedFromParents(father,mother);
	}
	public static boolean[] agreeToPair(Individual indA, Individual indB, float moment){
		return Arch.aie == null ? null : Arch.aie.agreeToPair(indA, indB, moment);
	}
	public static void pairIndividuals(Individual indA, Individual indB){
		if (Arch.aie != null) Arch.aie.pairIndividuals(indA, indB);
	}
	public static void divorce(Individual indA, Individual indB){
		if (Arch.aie != null) Arch.aie.divorce(indA, indB);
	}
	public void propagateReputation(Individual individual, float val) {
		if (Arch.aie != null) Arch.aie.propagateReputation(this, val);
	}

	public List<City> getAvailableMigrationTargets() {
		return Arch.aie == null ? null : Arch.aie.getAvailableMigrationTargets(this);
	}
	public void killIndividual(float deathDate){
		if (Arch.aie != null) Arch.aie.killIndividual(this,deathDate);
	}
	public void migrateTo(City nucity){
		if (Arch.aie != null) Arch.aie.migrateTo(this, nucity);
	}
	
	public float getGenderAttraction(Gender target) {
		return Arch.aie == null ? 0 : Arch.aie.getGenderAttraction(this, target);
	}
	public float[] getGendersAttraction() {
		return Arch.aie == null ? new float[2] : Arch.aie.getGendersAttraction(this);
	}
	
	public float getDesirability() {
		return Arch.aie == null ? 0 : Arch.aie.getDesirability(this);
	}
	
	public List<Individual> getAncestors(int lvl) {
		return Arch.aie == null ? null : Arch.aie.getAncestors(this,lvl);
	}
	
	public List<Individual> getDescendants(int lvl) {
		return Arch.aie == null ? null : Arch.aie.getDescendants(this,lvl);
	}
	
	public List<Individual> getRelatives(int lvl) {
		return Arch.aie == null ? null : Arch.aie.getRelatives(this,lvl);
	}
	public boolean update(float moment) {
		return Arch.aie == null ? null : Arch.aie.update(this,moment);
	}
	

	
	/*------------------------------------------------*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((individualID == null) ? 0 : individualID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		if (individualID == null) {
			if (other.individualID != null)
				return false;
		} else if (!individualID.equals(other.individualID))
			return false;
		return true;
	}
	
	public String toFileString() {
		StringBuilder res = new StringBuilder();
		res.append(individualID).append(FILESTRING_SEP);	//0
		res.append(name).append(FILESTRING_SEP);	//1
		res.append(surnameA).append(FILESTRING_SEP);	//2
		res.append(surnameB).append(FILESTRING_SEP);	//3
		res.append(gender == Gender.MALE ? FSTR_GM : FSTR_GF).append(FILESTRING_SEP);	//4
		res.append(StringUtils.df.format(birthDate)).append(FILESTRING_SEP);	//5
		res.append(StringUtils.df.format(deathDate)).append(FILESTRING_SEP);	//6
		res.append(originalZoneID).append(FILESTRING_SEP);	//7
		res.append(currentCityID).append(FILESTRING_SEP);	//8
		res.append(fatherID == null ? EMPTY_ID : fatherID).append(FILESTRING_SEP);	//9
		res.append(motherID == null ? EMPTY_ID : motherID).append(FILESTRING_SEP);	//10
		res.append(partnerID == null ? EMPTY_ID : partnerID).append(FILESTRING_SEP);	//11
		res.append(StringUtils.df.format(rep)).append(FILESTRING_SEP);	//12

		res.append(StringUtils.listToCompactString(childrenID, LIST_SEP)).append(FILESTRING_SEP);	//13
		res.append(StringUtils.listToCompactString(inventory, LIST_SEP)).append(FILESTRING_SEP);	//14
		res.append(genome.toFileString());	//15
		return res.toString();
	}

	@Override
	public String toString() {
		return "Individual [individualID=" + individualID + "]";
	}
	
	
}
