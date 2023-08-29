package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Fireball extends Entity {

	GamePanel gp;
	public int playNum;

	public OBJ_Fireball(GamePanel gp, int playNum, int worldX, int worldY) {
		super(gp);
		this.gp = gp;
		this.playNum = playNum;
		name = "fire ball";
		speed = 8;
		maxLife = 50;
		life = maxLife;
		atk = 20;
		useCort = 1;
		alive = false;
		MP = 20;

		// set location
		this.worldX = worldX;
		this.worldY = worldY;

		getImage();
	}

	public void getImage() {
		left = setup("/projectile/Fireball0_left", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/Fireball1_left", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/Fireball2_left", gp.tileSize, gp.tileSize);

		right = setup("/projectile/Fireball0_right", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/Fireball1_right", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/Fireball2_right", gp.tileSize, gp.tileSize);
	}

	public void setSolidArea() {
		// solidArea = new Rectangle(8,16,32,32));
		solidArea = new Rectangle();
		solidArea.x = worldX + 8;
		solidArea.y = worldY + 8;

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		solidArea.width = gp.tileSize - 8;
		solidArea.height = gp.tileSize - 8;

	}
	
	public void set(int worldX, int worldY, String direction, boolean alive) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.life = this.maxLife;
		setSolidArea();
	}

	public void update() {
		switch (direction) {
		case "left": {
			worldX -= speed;
			break;
		}
		case "right": {
			worldX += speed;
			break;
		}
		}

		life--;
		if (life <= 0) {
			alive = false;
		}

		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteCounter <= 15) {
				spriteNum = 0;
			}
			else if (spriteCounter <= 20) {
				spriteNum = 1;
			}else if(spriteCounter <= 25) {
				spriteNum = 2;
			}
			if(spriteCounter > 25) {
				spriteCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		switch (direction) {
		case "left":
			if (spriteNum == 0) {
				image = left;
			}
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}

			break;
		case "right":
			if (spriteNum == 0) {
				image = right;
			}
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		setSolidArea();
		g2.drawImage(image, worldX, worldY, null);
	}
}
