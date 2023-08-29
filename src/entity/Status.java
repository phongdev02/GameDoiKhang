package entity;

public class Status {

	public String name;
	public int HP;
	public int MP;
	public int crit;
	public int critDame;
	
	public Status(String name,int hP, int mP, int crit, int critDame) {
		this.name = name;
		this.HP = hP;
		this.MP = mP;
		this.crit = crit;
		this.critDame = critDame;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getMP() {
		return MP;
	}
	public void setMP(int mP) {
		MP = mP;
	}
	public int getCrit() {
		return crit;
	}
	public void setCrit(int crit) {
		this.crit = crit;
	}
	public int getCritDame() {
		return critDame;
	}
	public void setCritDame(int critDame) {
		this.critDame = critDame;
	}
}
