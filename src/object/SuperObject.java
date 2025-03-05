package object;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//PARENT CLASS FOR OTHER OBJECT CLASSES
public class SuperObject {
    private BufferedImage image, image1, image2, image3, image4, image5;
    private String name;
    private boolean collision = false;
    private int worldX;
    private int worldY;
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;


    //SETTERY
    public void setImage(BufferedImage image) { this.image = image; }
    public void setImage1(BufferedImage image1) { this.image1 = image1; }
    public void setImage2(BufferedImage image2) { this.image2 = image2; }
    public void setImage3(BufferedImage image3) { this.image3 = image3; }
    public void setImage4(BufferedImage image4) { this.image4 = image4; }
    public void setImage5(BufferedImage image5) { this.image5 = image5; }
    public void setName(String name) { this.name = name; }
    public void setWorldX(int worldX) { this.worldX = worldX; }
    public void setWorldY(int worldY) { this.worldY = worldY; }


    //GETTERY
    public String getName() { return name; }
    public boolean isCollision() { return collision; }
    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }
    public int getSolidAreaDefaultX() { return solidAreaDefaultX; }
    public int getSolidAreaDefaultY() { return solidAreaDefaultY; }
    public Rectangle getSolidArea() { return solidArea; }


    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

        if(gp.getPlayer().getWorldX() < gp.getPlayer().getScreenX()) {screenX = worldX;}                        //zatrzymaj kamere
        if(gp.getPlayer().getWorldY() < gp.getPlayer().getScreenY()) {screenY = worldY;}

        int rightOffset = gp.getScreenWidth() - gp.getPlayer().getScreenX();
        if(rightOffset > gp.getWorldWidth() - gp.getPlayer().getWorldX()) {
            screenX = gp.getScreenWidth() - (gp.getWorldWidth() - worldX);
        }

        int bottomOffset = gp.getScreenHeight() - gp.getPlayer().getScreenY();
        if(bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()) {
            screenY = gp.getScreenHeight() - (gp.getWorldHeight() - worldY);
        }

        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }

        else if(gp.getPlayer().getWorldX() < gp.getPlayer().getScreenX() ||
                gp.getPlayer().getWorldY() < gp.getPlayer().getScreenY() ||
                rightOffset > gp.getScreenWidth() - gp.getPlayer().getWorldX() ||
                bottomOffset > gp.getWorldHeight() - gp.getPlayer().getWorldY()) {
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
