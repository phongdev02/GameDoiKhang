package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import main.UI;
import object.OBJ_Fireball;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	UI ui;
	public CollisionChecker cChecker;
	int heightJump;
	int weight;
	int slashCount = 0;
	OBJ_Fireball fireBall;

	public Player(GamePanel gp, KeyHandler keyH, Status status) {
		super(gp);

		this.name = status.name;
		
		maxHP = status.HP;
		maxMP = status.MP;

		HP = maxHP;
		MP = maxMP;
		crit = status.crit;
		critDM = status.critDame;
		atk = 20;

		this.gp = gp;
		this.keyH = keyH;
		this.ui = new UI(gp);

		cChecker = new CollisionChecker(gp);
		jumpTurn = 0;
		heightJump = gp.tileSize * 3;

		// set location
		if (keyH.playerNum == 1) {

			setDefaultValues(gp, (2) * gp.tileSize);
			fireBall = new OBJ_Fireball(gp, 1, worldX, worldY);

		} else {

			setDefaultValues(gp, (gp.maxScreenCol - 3) * gp.tileSize);
			fireBall = new OBJ_Fireball(gp, 2, worldX, worldY);

		}
		setSolidArea();
		getPlayerImages();
	}

	public void setSolidArea() {
		// solidArea = new Rectangle(8,16,32,32));
		solidArea = new Rectangle();
		solidArea.x = worldX + 8;
		solidArea.y = worldY + 8;

		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		solidArea.width = gp.widthC - 8;
		solidArea.height = gp.heightC - 7 - (speeds * 3);

		// attackArea
		attackArea.width = gp.tileSize - 10;
		attackArea.height = gp.tileSize - 10;

	}

	public void setDefaultValues(GamePanel gp, int worldX) {
		this.worldX = worldX;
		this.worldY = (gp.maxScreenRow - 6) * gp.tileSize;
		speeds = 4;
		direction = "down";
		directionY = "down";
	}

	public void getPlayerImages() {
		try {
			if (keyH.playerNum == 1) {
				down = setup("/player/down", gp.tileSize, gp.heightC);

				left = setup("/player/left", gp.tileSize, gp.heightC);
				left1 = setup("/player/left1", gp.tileSize, gp.heightC);
				left2 = setup("/player/left2", gp.tileSize, gp.heightC);

				right = setup("/player/right", gp.tileSize, gp.heightC);
				right1 = setup("/player/right1", gp.tileSize, gp.heightC);
				right2 = setup("/player/right2", gp.tileSize, gp.heightC);

				// atk

				left_atk0 = setup("/player/left_atk0", gp.tileSize * 2, gp.heightC);
				left_atk1 = setup("/player/left_atk1", gp.tileSize * 2, gp.heightC);

				right_atk0 = setup("/player/right_atk0", gp.tileSize * 2, gp.heightC);
				right_atk1 = setup("/player/right_atk1", gp.tileSize * 2, gp.heightC);

			} else {
				down = setup("/NPC/down", gp.tileSize, gp.heightC);

				left = setup("/NPC/left", gp.tileSize, gp.heightC);
				left1 = setup("/NPC/left1", gp.tileSize, gp.heightC);
				left2 = setup("/NPC/left2", gp.tileSize, gp.heightC);

				right = setup("/NPC/right", gp.tileSize, gp.heightC);
				right1 = setup("/NPC/right1", gp.tileSize, gp.heightC);
				right2 = setup("/NPC/right2", gp.tileSize, gp.heightC);

				// atk
				left_atk0 = setup("/NPC/left_atk0", gp.tileSize * 2, gp.heightC);
				left_atk1 = setup("/NPC/left_atk1", gp.tileSize * 2, gp.heightC);

				right_atk0 = setup("/NPC/right_atk0", gp.tileSize * 2, gp.heightC);
				right_atk1 = setup("/NPC/right_atk1", gp.tileSize * 2, gp.heightC);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void update() {
		// allowed to continue causing damage
		

		if (this.keyH.upPressed == true && jumpTest == false) {
			directionY = "up";
			jumpTest = true;
			sprint();
		}

		checkJump();

		// falling
		if (keyH.leftPressed == true) {
			direction = "left";

			sprint();
		}
		if (keyH.rightPressed == true) {
			direction = "right";

			sprint();
		} else {
			spriteCounter++;
			if (spriteCounter > 10) {
				spriteNum = 0;
				spriteCounter = 0;
			}
		}
		
		if(direction == "left" || direction == "right") {
			//code shot Fire ball
			if (keyH.shotKeyPressed == true && fireBall.alive == false && gp.activeAttack) {
				// handle event on keypress
				if ((int) this.MP >= fireBall.MP) {
					fireBall.set(worldX, worldY, direction, true);
					this.MP -= fireBall.MP;
					gp.playSE(6);
				}
			}
			if (this.fireBall.alive) {
				fireBall.update();
				checkShot(this.direction);
			}
			
			// khi nhan key attack when not attacking
			if (keyH.charAttack == true && attacking == false) {
				attacking = true;
			}
			if (attacking == true) {
				attacking();
			}
		}
	}

	public void checkShot(String directionPlayer) {
		
		
		Player player;
		if (keyH.playerNum == 1) {
			player = gp.player2;
		} else {
			player = gp.player;
		}

		if (this.fireBall.solidArea.intersects(player.solidArea)) {
			player.HP -= outputDamage(this, this.fireBall.atk);
			if (player.HP <= 0) {

				if (keyH.playerNum == 1) {
					gp.numWinPlayer++;
				} else {
					gp.numWinPlayer2++;
				}

				gp.setPlayer();
			}

			gp.stopMusicSE();
			gp.playSE(7);

			this.fireBall.alive = false;

			switch (this.fireBall.direction) {
			case "left":
				for (int i = 0; i < 5; i++) {
					player.worldX -= speeds;
				}
				break;
			case "right":
				for (int i = 0; i < 5; i++) {
					player.worldX += speeds;
				}
				break;
			}
			
			//set time delay
			gp.setTimeBeginDelay();
			gp.setActiveAttack();
			
		}
	}

	public void attacking() {

		SwingSword++;

		Player player;
		if (keyH.playerNum == 1) {
			player = gp.player2;
		} else {
			player = gp.player;
		}

		if (SwingSword <= 5) {
			SwingNum = 0;
		}
		if (SwingSword > 5 && SwingSword <= 10)
			SwingNum = 1;
		if (SwingSword > 10 && SwingSword <= 25) {
			SwingNum = 2;
			// save the curren worldX, worldY, solidArea
			int currenWorldX = worldX;
			int currenWorldY = worldY;
			int currenAreaWidth = solidArea.width;
			int currenAreaHeight = solidArea.height;

			// adjust player's worldX/Y for the attackArea
			switch (direction) {
			case "left":
				worldX -= attackArea.width;
				break;
			case "right":
				worldX += attackArea.width;
				break;
			}

			// attackArea become solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			// check monster collision width the updated worldX, worldY, and solidArea

			checkAttack(this, player);

			// after checking collision, resotre the original data
			worldX = currenWorldX;
			worldY = currenWorldY;
			solidArea.width = currenAreaWidth;
			solidArea.height = currenAreaHeight;
			setSolidArea();
		}
		if (SwingSword > 25) {
			SwingNum = 0;
			SwingSword = 0;
			attacking = false;

			if (!this.activeAtk && !player.activeAtk && gp.activeAttack) {

				gp.setTimeBeginDelay();

				gp.setActiveAttack();

			}
		}
	}

	private boolean checkAttack(Player player, Player player2) {
		boolean test = false;
		setSolidArea();

		if (player.solidArea.intersects(player2.solidArea)) {
			if (gp.player.activeAtk == true && gp.player.activeAtk == true) {
				player2.HP -= outputDamage(player, player.atk);
				if (player2.HP <= 0) {

					if (keyH.playerNum == 1) {
						gp.numWinPlayer++;
					} else {
						gp.numWinPlayer2++;
					}

					gp.setPlayer();
				}

				if (player.MP + 20 <= player.maxMP) {
					player.MP += 20;
				} else {
					player.MP = player.maxMP;
				}

				if (player2.MP + 10 <= player2.maxMP) {
					player2.MP += 10;
				} else {
					player2.MP = player2.maxMP;
				}

				gp.player.activeAtk = false;
				gp.player2.activeAtk = false;

				//
				player2.collistionOn = false;
				player2.cChecker.checkTile(this);

				if (collistionOn == false) {
					switch (player.direction) {
					case "left":
						for (int i = 0; i < 5; i++) {
							player2.worldX -= speeds;
						}
						break;
					case "right":
						for (int i = 0; i < 5; i++) {
							player2.worldX += speeds;
						}
						break;
					}
				}

				test = true;
			}
		} else {
			test = false;
		}
		return test;
	}

	public void sprint() {
		// CHECK TILE COLLISION
		collistionOn = false;
		cChecker.checkTile(this);

		if (collistionOn == false) {
			switch (direction) {

			case "left":

				worldX -= speeds;

				break;
			case "right":
				worldX += speeds;

				break;
			}
		}

		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 0) {
				spriteNum = 1;
			}
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	private void checkJump() {

		collistionOnY = false;
		cChecker.checkfall(this);

		if (!collistionOnY) {
			switch (directionY) {
			case "up":
				if (jumpTest == true) {
					jumpTurn++;
					worldY -= speeds * 2;

					if (jumpTurn * (speeds * 2) >= heightJump) {
						directionY = "down";
					}
				}
				break;
			case "down":
				if (jumpTest == true) {
					worldY += speeds * 3;
				}
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); //fillRect(x1, y1, x2, y2) x1 y1 la location ban dau 
		BufferedImage image = null;
		int tempScreanX = worldX;
		int tempScreanY = worldY;

		switch (direction) {

		case "down":
			if (spriteNum == 0) {
				image = down;
			}
			break;
		case "left":
			if (attacking == false) {
				if (spriteNum == 0) {
					image = left;
				}
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
			} else {

				if (SwingNum == 0) {
					image = left;
				}
				if (SwingNum == 1) {
					tempScreanX -= gp.tileSize;
					image = left_atk0;
				}
				if (SwingNum == 2) {
					tempScreanX -= gp.tileSize;
					image = left_atk1;
				}
			}

			break;
		case "right":

			if (attacking == false) {
				if (spriteNum == 0) {
					image = right;
				}
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
			} else {

				if (SwingNum == 0) {
					image = right;
				}
				if (SwingNum == 1) {
					image = right_atk0;
				}
				if (SwingNum == 2) {
					image = right_atk1;
				}
			}
			break;
		}

		setSolidArea();

		g2.drawImage(image, tempScreanX, tempScreanY, null);

		if (fireBall.alive)
			fireBall.draw(g2);

		if (keyH.playerNum == 1) {
			ui.drawHP(g2, (2) * gp.tileSize, (2) * gp.tileSize, this.HP, this.maxHP);
			ui.drawMP(g2, (2) * gp.tileSize, (2) * gp.tileSize, this.MP, this.maxMP);
			ui.drawName(g2, this.name, worldX, worldY);
		} else {
			ui.drawName(g2, this.name, worldX, worldY);

			int barHP = (int) ((this.maxHP - this.HP) / (double) this.maxHP * 200);
			int barMP = (int) ((this.maxMP - this.MP) / (double) this.maxMP * 100);

			ui.drawHP(g2, (gp.maxScreenCol - 6) * gp.tileSize, (gp.maxScreenCol - 6) * gp.tileSize + barHP, this.HP,
					this.maxHP);
			ui.drawMP(g2, (gp.maxScreenCol - 6) * gp.tileSize + (200 - 100),
					(gp.maxScreenCol - 6) * gp.tileSize + (200 - 100) + barMP, this.MP, this.maxMP);

		}
	}
}
