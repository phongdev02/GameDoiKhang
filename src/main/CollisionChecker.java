package main;

import entity.Player;

public class CollisionChecker {

	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Player entity) {

		int entityLeftWorldX = entity.solidArea.x;
		int entityRightWorldX = entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.solidArea.y;
		int entityBottomWorldY = entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum01, tileNum02;

		switch (entity.direction) {

		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speeds) / gp.tileSize;

			tileNum01 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum02 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

			if (gp.tileManager.tile[tileNum01].collision == true || gp.tileManager.tile[tileNum02].collision == true) {
				entity.collistionOn = true;
			}

			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speeds) / gp.tileSize;

			tileNum01 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum02 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];

			if (gp.tileManager.tile[tileNum01].collision == true || gp.tileManager.tile[tileNum02].collision == true) {
				entity.collistionOn = true;
			}

			break;
		}

		checkfall(entity);
	}

	public void checkfall(Player entity) {

		int entityLeftWorldX = entity.solidArea.x;
		int entityRightWorldX = entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.solidArea.y;
		int entityBottomWorldY = entity.solidArea.y + entity.solidArea.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum01, tileNum02;

		switch (entity.directionY) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speeds * 3) / gp.tileSize;

			tileNum01 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum02 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];

			if (gp.tileManager.tile[tileNum01].collision == true || gp.tileManager.tile[tileNum02].collision == true) {
				entity.collistionOnY = true;
				entity.jumpTurn = 0;
				entity.directionY = "down";
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speeds * 3) / gp.tileSize;

			tileNum01 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum02 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];

			if (gp.tileManager.tile[tileNum01].collision == true || gp.tileManager.tile[tileNum02].collision == true) {

				entity.collistionOnY = true;
				entity.jumpTurn = 0;
				entity.jumpTest = false;

			} else {
				entity.jumpTest = true;
				entity.collistionOnY = false;
			}

			break;
		}

	}

//	public int checkObject(Entity entity, boolean player) {
//		int index = 999;
//
//		int i = 0;
//		for (var object : gp.obj) {
//			if (object != null) {
//				// get entity's solid area position
//				entity.solidArea.x = entity.worldX + entity.solidArea.x;
//				entity.solidArea.y = entity.worldY + entity.solidArea.y;
//
//				// get the object's solid area position
//				object.solidArea.x = object.worldX + object.solidArea.x;
//				object.solidArea.y = object.worldY + object.solidArea.y;
//
//				switch (entity.direction) {
//				case "up":
//					entity.solidArea.y -= entity.speeds;
//					if(entity.solidArea.intersects(object.solidArea)) {
//						
//					}
//					break;
//				case "down":
//					entity.solidArea.y += entity.speeds;
//					if(entity.solidArea.intersects(object.solidArea)) {
//						
//					}
//					break;
//				case "left":
//					entity.solidArea.x -= entity.speeds;
//					if(entity.solidArea.intersects(object.solidArea)) {
//						
//					}
//					break;
//				case "right":
//					entity.solidArea.x += entity.speeds;
//					if(entity.solidArea.intersects(object.solidArea)) {
//						
//					}
//					break;
//				}
//				entity.solidArea.x = entity.solidAreaDefaultX;
//				entity.solidArea.y = entity.solidAreaDefaultY;
//				
//				object.solidArea.x = object.solidAreaDefaultX;
//				object.solidArea.y = object.solidAreaDefaultY;
//			}
//			i++;
//		}
//
//		return index;
//	}
}
