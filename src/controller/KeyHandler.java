package controller;

import model.entity.Player;
import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    GamePanel gp;
    public static boolean upPressed, downPressed, leftPressed, rightPressed;
    public static boolean enterPresses = false;
    public static boolean esccapePresses = false;
    public static boolean movingKeyPressed = false;
    public Player player;

    //Debug
    public boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){ }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Title state
        if (gp.gameState == gp.titleState) { titleState(code);}

        //Play state
        if (gp.gameState == gp.playState) { playState(code); }

        //Option state
        if (gp.gameState == gp.optionState) {optionState(code);}

    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void playState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
            gp.ui.commanNum = 3;
        }

        if (!movingKeyPressed) {
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

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commanNum--;
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                if (gp.ui.commanNum > 3) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commanNum) {
                    //New game
                    case 0:
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        gp.ui.commanNum = 0;
                        break;
                    //Load game
                    case 1:
                        break;
                    //Setting
                    case 2:
                        gp.ui.titleScreenState = 1;
                        gp.ui.commanNum = 0;
                        break;
                    //Quit
                    case 3:
                        System.exit(0);
                        break;
                }

            }
        }
        else if (gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commanNum--;
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                if (gp.ui.commanNum > 3) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commanNum) {
                    //Mode
                    case 0:
                        //Mode screen
                        gp.ui.titleScreenState = 2;
                        gp.ui.commanNum = 0;
                        break;
                    //Music
                    case 1:
                        //Change music

                        //Return title screen 0
                        gp.ui.titleScreenState = 0;
                        gp.ui.commanNum = 0;
                        break;
                    //SE
                    case 2:
                        //Change SE

                        //Return title screen 0
                        gp.ui.titleScreenState = 0;
                        gp.ui.commanNum = 0;
                        break;
                    //Back
                    case 3:
                        //Return title screen 0
                        gp.ui.titleScreenState = 0;
                        gp.ui.commanNum = 0;
                        break;
                }
            }
        }
        else if (gp.ui.titleScreenState == 2) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commanNum--;
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                if (gp.ui.commanNum > 2) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commanNum) {
                    //Endless
                    case 0:
                        //Choose map

                        //Return title screen 0
                        gp.ui.titleScreenState = 1;
                        break;
                    //Overcome
                    case 1:
                        //Choose map

                        //Return title screen 0
                        gp.ui.titleScreenState = 1;
                        break;
                    //Back
                    case 2:
                        gp.ui.titleScreenState = 1;
                        gp.ui.commanNum = 0;
                        break;
                }
            }
        }
    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            gp.playSE(5);
            if (gp.ui.subState == 0) {
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 3;
                }
            }
            if (gp.ui.subState == 1) {
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 1;
                }
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            gp.playSE(5);
            if (gp.ui.subState == 0) {
                if (gp.ui.commanNum > 3) {
                    gp.ui.commanNum = 0;
                }
            }
            if (gp.ui.subState == 1) {
                if (gp.ui.commanNum > 1) {
                    gp.ui.commanNum = 0;
                }
            }
        }
        if (code == KeyEvent.VK_A) {
            if(gp.ui.subState == 0) {
                if (gp.ui.commanNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if (gp.ui.commanNum == 1 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(5);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if(gp.ui.subState == 0) {
                if (gp.ui.commanNum == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if (gp.ui.commanNum == 1 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(5);
                }
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.subState == 0) {
                switch (gp.ui.commanNum) {
                    //EndGame
                    case 2:
                        gp.ui.subState = 1;
                        gp.ui.commanNum = 0;
                        break;
                    //Back
                    case 3:
                        gp.gameState = gp.playState;
                        gp.ui.commanNum = 0;
                        break;
                }
            } else if (gp.ui.subState == 1) {
                switch (gp.ui.commanNum) {
                    //Yes
                    case 0:
                        gp.ui.subState = 0;
                        gp.gameState = gp.titleState;
                        break;
                    //No
                    case 1:
                        gp.ui.subState = 0;
                        gp.ui.commanNum = 2;
                        break;
                }
            }
        }
    }
}
