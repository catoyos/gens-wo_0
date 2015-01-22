package model;

import java.util.List;

public class Individual extends Storable {
	public static enum Gender { MALE, FEMALE }
	public static enum HairColor { WHITE, PLATINUM, BLONDE, LITEBROWN, RED, BROWN, AUBURN, BLACK }
	public static enum EyeColor { GRAY, BLUE, GREEN, HAZEL, BROWN, BLACK }

	private static long newIDn = 1l;
	public static void setNewIDn(long nid) { newIDn = nid; }
	public static long getNewIDn() { return newIDn; }

	private String individualID;
	private String name;
	private String surnameA;
	private String surnameB;
	private Gender gender;
	private float birthDate;
	private float deathDate;
	private String originalZone;
	private String currentCity;
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
	}
	
	public String getIndividualID() {
		return individualID;
	}
	public void setIndividualID(String individualID) {
		this.individualID = individualID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnameA() {
		return surnameA;
	}
	public void setSurnameA(String surnameA) {
		this.surnameA = surnameA;
	}
	public String getSurnameB() {
		return surnameB;
	}
	public void setSurnameB(String surnameB) {
		this.surnameB = surnameB;
	}
	public float getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(float birthDate) {
		this.birthDate = birthDate;
	}
	public float getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(float deathDate) {
		this.deathDate = deathDate;
	}
	public String getOriginalZone() {
		return originalZone;
	}
	public void setOriginalZone(String originalZone) {
		this.originalZone = originalZone;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public Genome getGenome() {
		return genome;
	}
	public void setGenome(Genome genome) {
		this.genome = genome;
	}
	public String getFatherID() {
		return fatherID;
	}
	public void setFatherID(String fatherID) {
		this.fatherID = fatherID;
	}
	public String getMotherID() {
		return motherID;
	}
	public void setMotherID(String motherID) {
		this.motherID = motherID;
	}
	public String getPartnerID() {
		return partnerID;
	}
	public void setPartnerID(String partnerID) {
		this.partnerID = partnerID;
	}
	public List<String> getChildrenID() {
		return childrenID;
	}
	public void setChildrenID(List<String> childrenID) {
		this.childrenID = childrenID;
	}
	public List<String> getInventory() {
		return inventory;
	}
	public void setInventory(List<String> inventory) {
		this.inventory = inventory;
	}
	public float getRep() {
		return rep;
	}
	public void setRep(float rep) {
		this.rep = rep;
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
	@Override
	public String toFileString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
