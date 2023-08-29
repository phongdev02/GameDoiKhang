package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Iterator;

import entity.Player;

public class UI {
	GamePanel gp;
	Graphics2D graphics2d;
	Font arialFt_40, arialFt_80;
	
	// BufferedImage bufferedImage;
	public boolean messageOn = false;
	public String message = "";
	public int messageCount = 0;
	public boolean gameFisnished = false;

	double playTime;
	DecimalFormat df = new DecimalFormat("0.0");

	public UI(GamePanel gp) {
		this.gp = gp;
		arialFt_40 = new Font("Arial", Font.PLAIN, 40);
		arialFt_80 = new Font("Arial", Font.BOLD, 80);
		// OBJ_Key key = new OBJ_Key(gp);
		// bufferedImage = key.image;
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.graphics2d = g2;

		g2.setFont(arialFt_40);
		g2.setColor(Color.white);

	}

	public void drawName(Graphics2D g2,String text,int X, int Y) {
		g2.setFont(new Font("Arial", Font.PLAIN, 20));

		//int x = X - gp.tileSize/3;
		int y = Y - gp.tileSize/2;

		int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = X - lenght / 2;
		
		g2.drawString(text,x,y);
	}
	
	public void drawHP(Graphics2D g2, int currserWidth, int width, int HP, int maxHP) {
		// Vẽ khung bao quanh thanh máu
		g2.setColor(Color.BLACK);
		g2.drawRect(currserWidth, 50, 200, 30);

		// Tính chiều dài thanh máu hiện tại
		int healthWidth = (int) (HP / (double) maxHP * 200);

		// Vẽ thanh máu
		g2.setColor(Color.RED);
		g2.fillRect(width, 50, healthWidth, 30);
		
		int rulHP = maxHP/50;
		
		for(int i = 0; i < rulHP; i++) {
			g2.setColor(Color.BLACK);
			g2.fillRect(currserWidth + 200/rulHP * (i+1), 50, 1, 30);
		}
	}

	public void drawMP(Graphics2D g2, int currserWidth, int width, int MP, int maxMP) {
		// border MP
		g2.setColor(Color.BLACK);
		g2.drawRect(currserWidth, 100, 100, 10);

		// width MP
		int healthWidth = (int) (MP / (double) maxMP * 100);

		// paint MP
		g2.setColor(Color.BLUE);
		g2.fillRect(width, 100, healthWidth, 10);
	}

	public void drawNumWin(Graphics2D g2d , int numWin1,int numWin2) {
		g2d.setFont(arialFt_40);
		
		String text = numWin1 + " - " + numWin2;
		int x = getXForCentedText(g2d,text);
		int y = gp.tileSize*2;

		g2d.drawString(text, x, y);
	}

	public void playerWin(Graphics2D g2d , int numWin1,int numWin2, Player player, Player player2) {
		g2d.setFont(arialFt_40);
		String text;
		int x = 0;
		int y = gp.maxScreenRow*gp.tileSize/2;

		if(numWin1 == 2) {
			text = player.name+" Win";
			x = getXForCentedText(g2d,text);
		}else {
			text = player2.name+" Win";
			x = getXForCentedText(g2d,text);
		}

		//g2d.drawRect(0,gp.screenHeight/2 - 180/2,gp.screenWidth,180);

        g2d.setColor(Color.RED);
        g2d.fillRect(0,  gp.screenHeight/2 - 180/2 - 40, gp.screenWidth, 180);
        
		
		g2d.setColor(Color.YELLOW);
		g2d.drawString(text, x, y);
		
	}


	public int getXForCentedText(Graphics2D g2d,String text) {
		int lenght = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int x = gp.screenWidth / 2 - lenght / 2;
		return x;
	}
}
