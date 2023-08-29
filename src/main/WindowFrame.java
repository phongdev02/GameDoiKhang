package main;

import javax.swing.JFrame;

import entity.Status;

public class WindowFrame extends JFrame{
	public static Status status1;
	public static Status status2;
	
	public WindowFrame(Status status1, Status status2) {
		
		this.status1 = status1;
		this.status2 = status2;
		
		// button close ON
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window not change 
		this.setResizable(false);
		
		//title
		this.setTitle("Game of Phong");
		
		GamePanel panelGame = new GamePanel(this);
		this.getContentPane().add(panelGame);	
		
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		// display 
		this.setVisible(true);
		
		panelGame.setUpGame();
		panelGame.startGameThread();
	}
}
