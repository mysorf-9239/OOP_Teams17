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

    //Debug
    public boolean showDebugText = false;

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

        //Title state
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {

                if (code == KeyEvent.VK_W) {
                    gp.ui.commanNum--;
                    if (gp.ui.commanNum < 0) {
                        gp.ui.commanNum = 4;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commanNum++;
                    if (gp.ui.commanNum > 4) {
                        gp.ui.commanNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.commanNum) {
                        //New game
                        case 0:
                            gp.gameState = gp.playState;
                            break;
                        //Load game
                        case 1:
                            break;
                        //Mode
                        case 2:
                            gp.ui.titleScreenState = 1;
                            break;
                        //Setting
                        case 3:
                            gp.ui.titleScreenState = 2;
                            break;
                        //Quit
                        case 4:
                            System.exit(0);
                            break;
                    }

                }
            }
            else if (gp.ui.titleScreenState == 1) {

                if (code == KeyEvent.VK_W) {
                    gp.ui.commanNum--;
                    if (gp.ui.commanNum < 0) {
                        gp.ui.commanNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commanNum++;
                    if (gp.ui.commanNum > 1) {
                        gp.ui.commanNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    switch (gp.ui.commanNum) {
                        //Endless
                        case 0:
                            break;
                        //Overcome
                        case 1:
                            break;
                    }
                }
            }
        }

        //Play state
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_ESCAPE) {
                if (gp.gameState == gp.playState) {
                    gp.gameState = gp.pauseState;
                } else if (gp.gameState == gp.pauseState) {
                    gp.gameState = gp.playState;
                }
            }

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

                //DeBug
                if (code == KeyEvent.VK_T) {
                    if (showDebugText == false) {
                        showDebugText = true;
                    } else if (showDebugText == true) {
                        showDebugText =false;
                    }
                }
                //Load map
                if (code == KeyEvent.VK_R) {
                    gp.tileManager.loadMap("/map/Maptest.txt");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}