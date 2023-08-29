package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Database.HistoryDAO;
import Database.LichSuDau;
import entity.Entity;
import entity.Player;
import entity.Status;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	WindowFrame windowFrame;
	
	//lich su dau
	
	// screen setting
	final int originalTileSize = 16; // 16x16 tile

	// * size
	final int scale = 3;
	
	// so lan chay
	int lanChay = 0;

	public final int tileSize = originalTileSize * scale; // 48 x 48 tile
	public final int heightC =  tileSize+(tileSize/3);
	public final int widthC = tileSize;
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768
	public final int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576
	
	public Status status;
	public Status status2;

	TileManager tileManager = new TileManager(this);
	KeyHandler keyH = new KeyHandler(1,this);
	KeyHandler keyH2 = new KeyHandler(2,this);
	Thread gameThread;
	public Player player;
	public Player player2;
	
	public int numWinPlayer = 0;
	public int numWinPlayer2 = 0;
	
	Sound music = new Sound();
	Sound musicSE = new Sound();
	public boolean testJump = false;
	
	//active game
	public boolean activeGame = true;
	public int round = 0;

//		public AssetSetter aSetter = new AssetSetter(this);
	
//		public SuperObject obj[] = new SuperObject[10];

	// WORLD SETTINGS
	// public final int maxWorldCol = 50;
	// public final int maxWorldRow = 50;
	// public final int worldWidth = tileSize * maxWorldCol;
	// public final int worldHeight = tileSize * maxWorldRow;

	//entity and project
	public ArrayList<Entity> projectileList = new ArrayList<>(); 
	//set time delay attack
	public long timeDelayBegin;
	private long timeDelayFinish;
	public boolean activeAttack = true;
	
	//paint HP
	public UI ui = new UI(this);
	
	// FPS
	int FPS = 60;

	public void closeMainFrame(){
		stopMusic();
		stopMusicSE();
		
		if(lanChay == 0) {
			
			
			new SetupFrame();
			lanChay++;
			if (windowFrame != null) {
	        	windowFrame.dispose(); // Đóng cửa sổ JFrame
	        }
		}
    }
	
	public GamePanel(WindowFrame windowFrame) {
		this.windowFrame = windowFrame;
		this.status = windowFrame.status1;
		this.status2 = windowFrame.status2;
		
		player = new Player(this, keyH, status);
		player2 = new Player(this, keyH2, status2);
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addKeyListener(keyH2);
		this.setFocusable(true);
		getVoid(round);
	}
	
	public void setPlayer() {
		round++;
		getVoid(round);
		if(numWinPlayer < 2 && numWinPlayer2 < 2) {
			
			player = new Player(this, keyH, this.status);
			player2 = new Player(this, keyH2, this.status2);
			
		}
		else {
			HistoryDAO history = new HistoryDAO();
			LichSuDau lSuDau = null ;
		
			if(numWinPlayer == 2)
				lSuDau = new LichSuDau(status.name, status2.name, status.name, status.HP, status.MP, status.crit, status.critDame);
			if(numWinPlayer2 == 2)
				lSuDau = new LichSuDau(status.name, status2.name, status.name, status.HP, status.MP, status.crit, status.critDame);
			
			if(lSuDau != null) {
				System.out.println(history.insertLichSu(lSuDau));
			}
				
			activeGame = false;
			round = 0;
		}
	}
	
	public void getVoid(int round) {
		if(round == 1) {
			//Round 1, FIGHT!
			playSE(8);
		}
		if(round == 2) {
			//Round 2, FIGHTdwd!
			playSE(9);
		}
		if(round == 3) {
			//Round 3, FIGHT!
			playSE(10);
		}
	}
	
	public void setUpGame() {
		
		playMusic(0);
		
		//stopMusic();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		Long LastTime = System.nanoTime();
		Long currenTime;
		Long timer = (long) 0;
		int drawCount = 0;

		while (gameThread != null) {

			currenTime = System.nanoTime();

			delta += ((currenTime - LastTime) / drawInterval);
			timer += (currenTime - LastTime);
			LastTime = currenTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				drawCount = 0;
				timer = (long) 0;
			}
//				try {
//					Thread.sleep(250);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
		}
	}

	public void update() {
		if(activeGame) {
			player.update();
			player2.update();
			
			if(!activeAttack) {
				 if(testDelay()) {
					 activeAttack = true;
					 player.activeAtk = true;
					 player.activeAtk = true;
					 System.out.println(timeDelayBegin);
				 }
			}
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		// TILE
		tileManager.draw(g2);

		// OBJECT

		//UI
		ui.drawNumWin(g2, numWinPlayer, numWinPlayer2);
		if(!activeGame) {
			ui.playerWin(g2, numWinPlayer, numWinPlayer2, player, player2);
		}
		
		// PLAYER
		player.draw(g2);
		player2.draw(g2);

		g2.dispose();
	}
	
	//delay attack
	public void setTimeBeginDelay() {
		timeDelayBegin = System.nanoTime();
	}
	private void setTimeBegin() {
		timeDelayBegin = 0;
	}
	private boolean testDelay() {
		boolean test = false;
		timeDelayFinish = System.nanoTime();
		if(timeDelayFinish - timeDelayBegin >= 1000000000)
			test = true;
		return test;
	}
	public void setActiveAttack() {
		activeAttack = false;
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.Play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void stopMusicSE() {
		musicSE.stop();
	}
	
	public void playSE(int i) {
		musicSE.setFile(i);
		musicSE.Play();
	}
}
