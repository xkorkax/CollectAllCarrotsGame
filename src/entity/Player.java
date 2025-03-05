package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    private GamePanel gp;
    private KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private int hasCarrots = 0;

    //KONSTRUKTOR
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.getScreenWidth()/2 - (gp.getTileSize()/2);                     //odejmujemy polowe wielkosci kafelka, ponieważ współrzędne odnoszą się do lewego gorego roku kafelka
        screenY = gp.getScreenHeight()/2 - (gp.getTileSize()/2);

        //chcemy aby nie caly kafelek postaci był stały - ulatwienie poruszania sie w grze
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }


    //GETTERY
    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
    public int getHasCarrots() { return hasCarrots; }



    //METODY
    public void setDefaultValues(){
        //starting position
        setWorldX(gp.getTileSize() * 10);
        setWorldY(gp.getTileSize() * 10);
        setSpeed(4);
        direction = "right";
        lastDirection = "right";
    }

    public void getPlayerImage(){
        try{
            r1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1R.png"));
            r2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2R.png"));
            r3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3R.png"));
            r4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4R.png"));
            r5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5R.png"));
            r6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6R.png"));

            l1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1L.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2L.png"));
            l3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3L.png"));
            l4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4L.png"));
            l5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5L.png"));
            l6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6L.png"));

            ur1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1RU.png"));
            ur2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2RU.png"));
            ur3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3RU.png"));
            ur4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4RU.png"));
            ur5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5RU.png"));
            ur6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6RU.png"));

            ul1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1LU.png"));
            ul2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2LU.png"));
            ul3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3LU.png"));
            ul4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4LU.png"));
            ul5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5LU.png"));
            ul6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6LU.png"));

            dr1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1RD.png"));
            dr2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2RD.png"));
            dr3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3RD.png"));
            dr4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4RD.png"));
            dr5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5RD.png"));
            dr6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6RD.png"));

            dl1 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny1LD.png"));
            dl2 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny2LD.png"));
            dl3 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny3LD.png"));
            dl4 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny4LD.png"));
            dl5 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny5LD.png"));
            dl6 = ImageIO.read(getClass().getResourceAsStream("/rabbit/bunny6LD.png"));
        } catch(IOException e){
            e.printStackTrace();
        };
    }

    public void update(){

        if(keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
            if(keyH.isUpPressed()) {direction = "up";}
            else if(keyH.isDownPressed()) {direction = "down";}
            else if(keyH.isLeftPressed()) {direction = "left";}
            else if(keyH.isRightPressed()){direction = "right";}

            if(!Objects.equals(direction, "down") &&  !Objects.equals(direction, "up")){
                lastDirection = direction;
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.getcChecker().checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObject(objIndex);

            if(collisionOn == false){
                switch(direction){
                    case "up":
                        setWorldY(getWorldY() - getSpeed()); break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed()); break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed()); break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed()); break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 2){
                if(spriteNum == 6){
                    spriteNum = 1;
                } else spriteNum++;
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){                                //metoda ktora na podstawie indexu okresla co robimy z kazdym przedmiotem
        if(i != 999){                                               //jesli index wynosi 999 to nie dotykamy zadnego obiektu
            String objectName = gp.obj[i].getName();
            switch(objectName){                                     //w tym mojecu mozemy dodac reakcje na inne przedmioty
                case "carrot":
                    hasCarrots++;
                    gp.obj[i] = null;                               //usuniecie dotknietego obiektu
                    if(hasCarrots == 6){
                        gp.getUi().gameFinished = true;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(Objects.equals(lastDirection, "left")){
                    if(spriteNum == 1) image = ul1;
                    if(spriteNum == 2) image = ul2;
                    if(spriteNum == 3) image = ul3;
                    if(spriteNum == 4) image = ul4;
                    if(spriteNum == 5) image = ul5;
                    if(spriteNum == 6) image = ul6;
                } else if(Objects.equals(lastDirection, "right")){
                    if(spriteNum == 1) image = ur1;
                    if(spriteNum == 2) image = ur2;
                    if(spriteNum == 3) image = ur3;
                    if(spriteNum == 4) image = ur4;
                    if(spriteNum == 5) image = ur5;
                    if(spriteNum == 6) image = ur6;
                }
                break;
            case "down":
                if(Objects.equals(lastDirection, "left")){
                    if(spriteNum == 1) image = dl1;
                    if(spriteNum == 2) image = dl2;
                    if(spriteNum == 3) image = dl3;
                    if(spriteNum == 4) image = dl4;
                    if(spriteNum == 5) image = dl5;
                    if(spriteNum == 6) image = dl6;
                } else if(Objects.equals(lastDirection, "right")){
                    if(spriteNum == 1) image = dr1;
                    if(spriteNum == 2) image = dr2;
                    if(spriteNum == 3) image = dr3;
                    if(spriteNum == 4) image = dr4;
                    if(spriteNum == 5) image = dr5;
                    if(spriteNum == 6) image = dr6;
                }
                break;
            case "left":
                if(spriteNum == 1) image = l1;
                if(spriteNum == 2) image = l2;
                if(spriteNum == 3) image = l3;
                if(spriteNum == 4) image = l4;
                if(spriteNum == 5) image = l5;
                if(spriteNum == 6) image = l6;
                break;
            case "right":
                if(spriteNum == 1) image = r1;
                if(spriteNum == 2) image = r2;
                if(spriteNum == 3) image = r3;
                if(spriteNum == 4) image = r4;
                if(spriteNum == 5) image = r5;
                if(spriteNum == 6) image = r6;
                break;
        }
        int x = screenX;
        int y = screenY;

        if(screenX > getWorldX()){
            x = getWorldX();
        }
        if(screenY > getWorldY()){
            y = getWorldY();
        }

        int rightOffset = gp.getScreenWidth() - screenX;
        if(rightOffset > gp.getWorldWidth() - getWorldX()){
            x = gp.getScreenWidth() - (gp.getWorldWidth()- getWorldX());
        }
        int bottomOffset = gp.getScreenHeight() - screenY;
        if(bottomOffset > gp.getWorldHeight() - getWorldY()){
            y = gp.getScreenHeight() - (gp.getWorldHeight()- getWorldY());
        }

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }
}
