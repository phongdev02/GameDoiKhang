package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	//new WindowFrame();
            	new SetupFrame().setVisible(true);
            }
        });
	}

}
