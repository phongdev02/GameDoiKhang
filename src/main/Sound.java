package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[]  = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");//jump
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/background-219.wav");
		soundURL[6] = getClass().getResource("/sound/fire.wav");
		soundURL[7] = getClass().getResource("/sound/burning.wav");
		soundURL[8] = getClass().getResource("/sound/round1.wav");
		soundURL[9] = getClass().getResource("/sound/round2.wav");
		soundURL[10] = getClass().getResource("/sound/round3.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void Play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
