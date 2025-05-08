package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }
    
    public void getTileImage() {
        setup(0, "grass00.png", false);
        setup(1, "wall.png", true);
        setup(2, "water00.png", false);
        setup(3, "earth.png", false);
        setup(4, "tree.png", true);
        setup(5, "road00.png", false);

//        setup(0, "grass00.png", false);
//        setup(1, "grass01.png", false);
//        setup(2, "water00.png", true);
//        setup(3, "water01.png", true);
//        setup(4, "water02.png", true);
//        setup(5, "water03.png", true);
//        setup(6, "water04.png", true);
//        setup(7, "water05.png", true);
//        setup(8, "water06.png", true);
//        setup(9, "water07.png", true);
//        setup(10, "water08.png", true);
//        setup(11, "water09.png", true);
//        setup(12, "water10.png", true);
//        setup(13, "water11.png", true);
//        setup(14, "water12.png", true);
//        setup(15, "water13.png", true);
//        setup(16, "road00.png", false);
//        setup(17, "road01.png", false);
//        setup(18, "road02.png", false);
//        setup(19, "road03.png", false);
//        setup(20, "road04.png", false);
//        setup(21, "road05.png", false);
//        setup(22, "road06.png", false);
//        setup(23, "road07.png", false);
//        setup(24, "road08.png", false);
//        setup(25, "road09.png", false);
//        setup(26, "road10.png", false);
//        setup(27, "road11.png", false);
//        setup(28, "road12.png", false);
//        setup(29, "earth.png", false);
//        setup(30, "wall.png", true);
//        setup(31, "tree.png", true);
    }
    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
            tile[index].image = uTool.scareImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
