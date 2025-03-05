package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class Main {

    //KONSTRUKTOR
    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Carrots");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();                                                                      //dopasowanie do rozmiaru okna
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
