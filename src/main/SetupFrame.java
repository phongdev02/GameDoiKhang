package main;

import javax.swing.JFrame;

public class SetupFrame extends JFrame{
	public SetupFrame() {
		
		// button close ON
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//window not change 
		this.setResizable(false);
		
		//title
		this.setTitle("Game of Phong");
		
		SetupChar setupChar = new SetupChar(this);
		this.add(setupChar);
//		GamePanel panelGame = new GamePanel();
//		windowFrame.add(panelGame);	
		
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		// display 
		this.setVisible(true);
		
//		panelGame.setUpGame();
//		panelGame.startGameThread();
	}
}
