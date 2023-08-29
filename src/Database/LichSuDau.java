package Database;

public class LichSuDau {
	private int ID;
	private String Player1;
	private String Player2;
	private String playerWin;
	private int HP;
	private int MP;
	private int crit;
	private int CritDame;
	
	public LichSuDau(String player1, String player2, String playerWin, int hP, int mP, int crit, int critDame) {
		Player1 = player1;
		Player2 = player2;
		this.playerWin = playerWin;
		HP = hP;
		MP = mP;
		this.crit = crit;
		CritDame = critDame;
	}
	public String getPlayer1() {
		return Player1;
	}
	public void setPlayer1(String player1) {
		Player1 = player1;
	}
	public String getPlayer2() {
		return Player2;
	}
	public void setPlayer2(String player2) {
		Player2 = player2;
	}
	public String getPlayerWin() {
		return playerWin;
	}
	public void setPlayerWin(String playerWin) {
		this.playerWin = playerWin;
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
		return CritDame;
	}
	public void setCritDame(int critDame) {
		CritDame = critDame;
	}
	@Override
	public String toString() {
		return " [getPlayer1()=" + getPlayer1() + ", getPlayer2()=" + getPlayer2() + ", getPlayerWin()="
				+ getPlayerWin() + ", getHP()=" + getHP() + ", getMP()=" + getMP() + ", getCrit()=" + getCrit()
				+ ", getCritDame()=" + getCritDame() + "]";
	}
	
	
}
