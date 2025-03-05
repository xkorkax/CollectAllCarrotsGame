package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{                              //Runnable - interfejs

    //SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16[pixels] tile
    private final int scale = 3;                                                        //skalujemy, bo obecnie monitory maja znacznie większą rozdzielczość
    private final int tileSize = originalTileSize * scale;                              //3x16=48, bedzie wyglądac na 48x48
    private final int maxNumberOfScreenCol = 16;
    private final int maxNumberOfScreenRow = 12;
    private final int screenWidth = tileSize * maxNumberOfScreenCol;                    //rzeczywisty rozmiar okna gry: 768x576pixeli
    private final int screenHeight = tileSize * maxNumberOfScreenRow;

    //WORLD SETTINGS
    private final int maxWorldCol = 31;
    private final int maxWorldRow = 21;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;


    private final int FPS = 60;                                                         //odswiezanie ekranu x razy na sekunde


    //DEKARACJA OBDLUGI KLAWIATURY
    private KeyHandler keyHandler = new KeyHandler();
    private TileManager tileM = new TileManager(this);


    private UI ui = new UI(this);


    //MECHANIKA GRY
    public Thread gameThread;
    private final CollisionChecker cChecker = new CollisionChecker(this);
    private final AssetSetter aSetter = new AssetSetter(this);
    private final Player player = new Player(this, keyHandler);


    public SuperObject[] obj = new SuperObject[10];                                     //mozliwosc wysliwtlenia do 10 przedmiotow jednoczesnie na ekranie, zbyt wiele przedmiotow moze spowolnic znaczaco gre


    //GAME STATE - mozna dodac wiecej stanow gry
    private int gameState;
    private final int playState = 1;


    //KONSTRUKTOR KLASY
    public GamePanel() throws IOException, FontFormatException {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);                                                             //setFocusable - Panel bedzie skupiony na przejmowaniu inputu z klawiatury
    }



    //GETTERY
    public int getTileSize() {return tileSize;}
    public int getScreenWidth() { return screenWidth; }
    public int getScreenHeight() { return screenHeight; }
    public int getMaxWorldCol() { return maxWorldCol; }
    public int getMaxWorldRow() { return maxWorldRow; }
    public int getWorldWidth() {return worldWidth; }
    public int getWorldHeight() {return worldHeight; }
    public TileManager getTileM() { return tileM; }
    public Player getPlayer() { return player; }
    public CollisionChecker getcChecker() {return cChecker; }
    public UI getUi() { return ui; }



    //METODY
    public void setupGame(){
        aSetter.setObject();
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;  //returns time in nanoseconds

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            if(delta >= 0){
                //1. UPDATE
                update();
                //2. DRAW                                                                   //wywołujemy metode paintComponent() - wbudowana w Jave
                repaint();                                                                  //repaint - tak wywołujemy metode paint component
                delta--;
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();
        }
    }

    //Graphics - a class that has many functions to draw objects on the screen
    //Graphics2D - class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management and text layout

    public void paintComponent(Graphics g){
        super.paintComponent(g);                                                            //super - the parent class of the class
        Graphics2D g2 = (Graphics2D)g;
            //TILE
            tileM.draw(g2);
            //PLAYER
            player.draw(g2);
            //UI
            ui.draw(g2);
            //OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            g2.dispose();                                                                     //dobra praktyka pozwalajaca zaoszczedzic pamiec
    }
}
