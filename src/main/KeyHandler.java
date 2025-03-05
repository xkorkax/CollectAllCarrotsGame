package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    //GETTERY
    public boolean isUpPressed() {return upPressed; }
    public boolean isDownPressed() { return downPressed; }
    public boolean isLeftPressed() { return leftPressed; }
    public boolean isRightPressed() { return rightPressed; }

    @Override
    public void keyTyped(KeyEvent e) {                                  //musimy dodac, bo wymaga tego interfejs KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();                                      //getKeyCode() - zwraca (int) kod powiązany z danym przyciskiem na klawiaturze

        if(code == KeyEvent.VK_UP) {upPressed = true;}
        if(code == KeyEvent.VK_DOWN) {downPressed = true;}
        if(code == KeyEvent.VK_LEFT) {leftPressed = true;}
        if(code == KeyEvent.VK_RIGHT) {rightPressed = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {upPressed = false;}
        if(code == KeyEvent.VK_DOWN) {downPressed = false;}
        if(code == KeyEvent.VK_LEFT) {leftPressed = false;}
        if(code == KeyEvent.VK_RIGHT) {rightPressed = false;}
    }
}
