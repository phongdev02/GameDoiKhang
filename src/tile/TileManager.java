package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		this.tile = new Tile[10];
		this.mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		loadMap("/maps/map.txt");
		getImageTile();
	}

	public void getImageTile() {
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.jpg"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/eath.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col< gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				
				while(col<gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxScreenCol) {
					col=0;
					row++;
				}
			}
			
			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void draw(Graphics2D g2) {
		//g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize,null);
		
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol*gp.tileSize;
			int worldY = worldRow*gp.tileSize;
			
			g2.drawImage(tile[tileNum].image, worldX, worldY, gp.tileSize, gp.tileSize,null);
			
			worldCol++;

			
			if(worldCol == gp.maxScreenCol) {
				worldCol = 0;

				worldRow++;

			}
			
		}
		
	}
	
}
