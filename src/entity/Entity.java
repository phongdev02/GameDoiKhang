package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	
	public int worldX,worldY;
	public int speeds;
	
	//character stats
	public int maxHP;
	public int maxMP;
	
	public int HP;
	public int MP;
	public int atk;
	public int crit;
	public int critDM;
	public boolean activeAtk = true;
	public boolean activeAtkMP = true;
	public boolean alive = true;
	
	//character attribute
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	
	//item attribute
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCort;
	
	
	public BufferedImage left, left1, left2, right, right1, right2, down;
	public BufferedImage left_atk0, left_atk1, right_atk0, right_atk1, left_atk, right_atk;
	public String direction;
	
	//check Y when jump
	public String directionY;
	public boolean jumpTest = false;
	public int jumpTurn ;
	
	//atk
	public int SwingSword = 0;
	public int SwingNum = 0;
	public boolean attacking = false;
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	
	public int spriteCounter = 0;
	public int spriteNum = 0;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	// va cham 
	public Rectangle solidArea;
	public boolean collistionOn = false;
	public boolean collistionOnY = false;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public int outputDamage(Player player, int atk) {
		int sum = 0;
		
		//total damage
		boolean isCritical = isCriticalHit(player.crit);

        if (isCritical) {
            sum += (atk + (atk * player.critDM/100));
        } else {
            sum += atk;
        }
		
		return sum;
	}
	
	private static boolean isCriticalHit(int critRate) {
        Random random = new Random();
        int randomValue = (int) (random.nextInt() * 100.0); 
        return randomValue <= critRate;
    }
}
