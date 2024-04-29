package controller;

import model.entity.Player;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    GamePanel gp;
    public static boolean upPressed, downPressed, leftPressed, rightPressed;
    public Player player;
    public static boolean movingKeyPressed = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            synchronized(player) {
                upPressed = true;
            }
        }
        if(code == KeyEvent.VK_S) {
            synchronized(player) {
                downPressed = true;
            }
        }
        if(code == KeyEvent.VK_A) {
            synchronized(player) {
                leftPressed = true;
            }
        }
        if(code == KeyEvent.VK_D) {
            synchronized(player) {
                rightPressed = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (movingKeyPressed) {
            return;
        } else {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
                movingKeyPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
                movingKeyPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
                movingKeyPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
                movingKeyPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                }
                else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}