package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private int worldX, worldY;
    private int speed;
    public BufferedImage r1, r2, r3, r4, r5, r6, l1, l2, l3, l4, l5, l6, dl1, dl2, dl3, dl4, dl5, dl6, dr1, dr2, dr3, dr4, dr5, dr6, ul1, ul2, ul3, ul4, ul5, ul6, ur1, ur2, ur3, ur4, ur5, ur6;
    public String direction;
    public String lastDirection;
    public int spriteCounter = 0;
    public  int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;


    public int getWorldX() { return worldX; }
    public void setWorldX(int worldX) { this.worldX = worldX; }
    public int getWorldY() { return worldY; }
    public void setWorldY(int worldY) { this.worldY = worldY; }
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }

}
