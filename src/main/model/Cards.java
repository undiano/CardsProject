package main.model;

public class Cards {

	private int id;
	private String name;
	private int summonCost;
	private int attack;
	private int defense;
	private int value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSummonCost() {
		return summonCost;
	}
	public void setSummonCost(int summonCost) {
		this.summonCost = summonCost;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Cards [id=" + id + ", name=" + name + ", summonCost=" + summonCost + ", attack=" + attack + ", defense="
				+ defense + ", value=" + value + "]";
	}
	
	
}
