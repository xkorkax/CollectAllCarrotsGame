package main;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private final Font maruMonica_40, maruMonica_80;
    private boolean messageOn = false;
    private String message = "";
    public boolean gameFinished = false;


    //KONSTRUKTOR
    public UI(GamePanel gp) {
        this.gp = gp;
        maruMonica_40 = new Font("x12y16pxMaruMonica", Font.BOLD, 40);
        maruMonica_80 = new Font("x12y16pxMaruMonica", Font.BOLD, 80);
    }

    //METODY
    public void draw(Graphics2D g2){
        if(gameFinished){
            g2.setFont(maruMonica_40);
            g2.setColor(Color.white);

            String text;
            int textLength, x, y;
            text = "All carrots are collected";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.getScreenWidth() - textLength)/2;                                               //przesuniecie, bo wspolrzedne wskazuja na poczatek tekstu
            y = gp.getScreenHeight() /2 - (gp.getTileSize() *3);
            g2.drawString(text, x, y);

            g2.setFont(maruMonica_80);
            g2.setColor(Color.white);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.getScreenWidth() - textLength)/2;
            y = gp.getScreenHeight() /2 + (gp.getTileSize() *2);
            g2.drawString(text, x, y);
            gp.gameThread = null;                                                                   //stopping the game
        } else {
            g2.setFont(maruMonica_40);
            g2.setColor(Color.black);
            g2.drawString("carrots: " + gp.getPlayer().getHasCarrots(), 25, 50);

            //MESSAGE
            if(messageOn){
                g2.drawString(message, gp.getTileSize() /2, gp.getTileSize() *5);
            }
        }
    }
}
