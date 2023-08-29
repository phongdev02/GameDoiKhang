package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;

public class KeyHandler implements KeyListener {
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;
	public boolean charAttack = false;
	public boolean shotDebugText = false;
	public boolean closeWindown ;
	public boolean pauseGame;
	public int playerNum ;

	public KeyHandler(int player, GamePanel gp) {
		this.gp = gp;
		this.playerNum = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_SPACE) {
			closeWindown = true;
			
		}
		
		if(closeWindown) {
			if(!gp.activeGame) {
				gp.activeGame = true;
				gp.round = 0;
				//new GamePanel(gp.status, gp.status2);
				gp.setPlayer();
			}
		}
		
		if(code == KeyEvent.VK_T) {
			if(!gp.activeGame) {
				gp.closeMainFrame();
			}
		}

		if(this.playerNum == 1) {
			// player 1
			
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_B) {
				charAttack = true;
			}
			if(code == KeyEvent.VK_N) {
				shotKeyPressed = true;
			}
		}
		else {
			// player 2
			
			if (code == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_OPEN_BRACKET) {
				charAttack = true;
			}
			if(code == KeyEvent.VK_CLOSE_BRACKET) {
				shotKeyPressed = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_SPACE) {
			closeWindown = false;	
		}
		
		if(this.playerNum == 1) {
			// player 1
			if (code == KeyEvent.VK_W) {
				upPressed = false;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = false;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = false;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = false;
			}
			if(code == KeyEvent.VK_B) {
				charAttack = false;
			}
			if(code == KeyEvent.VK_N) {
				shotKeyPressed = false;
			}
		}
		else {
			// player 2
			if (code == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (code == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (code == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (code == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if(code == KeyEvent.VK_OPEN_BRACKET) {
				charAttack = false;
			}
			if(code == KeyEvent.VK_CLOSE_BRACKET) {
				shotKeyPressed = false;
			}
		}
	}
}
