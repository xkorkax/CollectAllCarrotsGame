package tile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int[][] mapTileNum;

    //KONSTRUKTOR
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];                                                                                //tworzymy 10 różnych kafelków
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/world2.txt");
    }

    //GETTERY
    public Tile[] getTile() { return tile; }
    public int[][] getMapTileNum() { return mapTileNum; }


    //METODY
    public void getTileImage(){
        try{

            tile[0] = new Tile(); //trawa1
            tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png")));

            tile[1] = new Tile(); //trawa2
            tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png")));

            tile[2] = new Tile(); //kwiatki niebieskie
            tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/flowers_blue.png")));
            tile[2].collision = true;

            tile[3] = new Tile(); //kwiatki rozowe
            tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/flowers_pink.png")));
            tile[3].collision = true;

            tile[4] = new Tile(); //skaly1
            tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/rocks1.png")));
            tile[4].collision = true;

            tile[5] = new Tile(); //skaly2
            tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/rocks2.png")));
            tile[5].collision = true;

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()){
                String line = br.readLine();                                                        //czytamy linie tekstu
                while(col < gp.getMaxWorldCol()){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);                                       //zmieniamy string na inta
                    mapTileNum[col][row] = num;
                    col++;
                }
                while(col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e){}
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()){

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();

            //informacja, gdzie na ekranie mamy narysowac dany kafelek
            int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();                                                            //dodając gp.player.screenX zapewniamy sobie, że jeśli gracz stoi np. w lewym górnym rogu to jego pozycja się zmieni ze środka i na calym ekranie narysowane będą kafelki tła
            int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

            //stop moving the camera at the edge
            if(gp.getPlayer().getScreenX() > gp.getPlayer().getWorldX()) {screenX = worldX;}
            if(gp.getPlayer().getScreenY() > gp.getPlayer().getWorldY()) {screenY = worldY;}

            int rightOffset = gp.getScreenWidth() - gp.getPlayer().getScreenX();
            if(rightOffset > gp.getWorldWidth() - gp.getPlayer().getWorldX()){
                screenX = gp.getScreenWidth() - (gp.getWorldWidth()- worldX);
            }

            int bottomOffset = gp.getScreenHeight() - gp.getPlayer().getScreenY();
            if(bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()){
                screenY = gp.getScreenHeight() - (gp.getWorldHeight()- worldY);
            }

            //rysujemy tylko kafelki, które widocznie są na ekranie gry
            if(worldX + gp.getTileSize()> gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                    worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                    worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                    worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
                //rysujemy kafelek o numerze, ktory zapisany jest w mapie
                g2.drawImage(tile[mapTileNum[worldCol][worldRow]].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            } else if(gp.getPlayer().getScreenX() > gp.getPlayer().getWorldX() ||
                    gp.getPlayer().getScreenY() > gp.getPlayer().getWorldY() ||
                    rightOffset > gp.getWorldWidth() - gp.getPlayer().getWorldX() ||
                    bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()){
                g2.drawImage(tile[mapTileNum[worldCol][worldRow]].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
            }
            worldCol++;

            if(worldCol == gp.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
