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

        switch (gp.gameState) {
            case 0:
                titleState(code);
                break;
            case 1:
                playState(code);
                break;
            case 2:
                optionState(code);
                break;
            case 3:
                gameOverState(code);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void playState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
            gp.stopMusic();
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

            //Cut down the tree
            if (code == KeyEvent.VK_C) {

                int plCol = (gp.player.worldX + gp.player.solidArea.x)/gp.titleSize;
                int plRow  = (gp.player.worldY + gp.player.solidArea.y)/gp.titleSize;

                int tileAddress = gp.tileManager.checkTile(plCol, plRow);
                int tileCol = 0;
                int tileRow = 0;

                if (tileAddress >= 0 && gp.player.hasAxe > 0) {

                    tileCol = tileAddress % gp.maxWorldRow;
                    tileRow = tileAddress / gp.maxWorldRow;

                    gp.player.hasAxe -= 1;
                    gp.tileManager.cutTree(tileCol, tileRow);
                }
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
                gp.tileManager.loadMap(gp.currentMap);
            }
        }
    }

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commanNum--;
                gp.playSE(5);
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                gp.playSE(5);
                if (gp.ui.commanNum > 3) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                switch (gp.ui.commanNum) {
                    //New game
                    case 0:
                        if (gp.gameMode == 0) {
                            gp.currentMap = 0;
                            gp.player.getPlayerImage();
                            gp.player.setDefaultValues();
                            gp.newGame();
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                        } else if (gp.gameMode > 0) {
                            gp.ui.titleScreenState = 3;
                        }
                        gp.ui.commanNum = 0;
                        break;
                    //Load game
                    case 1:
                        if (gp.gameMode == 0) {
                            gp.currentMap = 0;
                            gp.loadGame();
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                        } else if (gp.gameMode > 0) {
                            gp.ui.titleScreenState = 3;
                        }
                        gp.ui.commanNum = 0;
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
                gp.playSE(5);
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 4;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                gp.playSE(5);
                if (gp.ui.commanNum > 4) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_A) {
                switch (gp.ui.commanNum) {
                    case 0:
                        if (gp.gameMode == gp.endlessMode) {
                            gp.gameMode = gp.overcomeMode;
                        } else {
                            gp.gameMode = gp.endlessMode;
                        }
                        gp.playSE(5);
                        break;
                    case 1:
                        if (gp.music.volumeScale > 0) {
                            gp.music.volumeScale--;
                            gp.music.checkVolume();
                        }
                        gp.playSE(5);
                        break;
                    case 2:
                        if (gp.se.volumeScale > 0) {
                            gp.se.volumeScale--;
                        }
                        gp.playSE(5);
                        break;

                }
            }
            if (code == KeyEvent.VK_D) {
                switch (gp.ui.commanNum) {
                    case 0:
                        if (gp.gameMode == gp.endlessMode) {
                            gp.gameMode = gp.overcomeMode;
                        } else {
                            gp.gameMode = gp.endlessMode;
                        }
                        gp.playSE(5);
                        break;
                    case 1:
                        if (gp.music.volumeScale < 5) {
                            gp.music.volumeScale++;
                            gp.music.checkVolume();
                        }
                        gp.playSE(5);
                        break;
                    case 2:
                        if (gp.se.volumeScale < 5) {
                            gp.se.volumeScale++;
                        }
                        gp.playSE(5);
                        break;

                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                switch (gp.ui.commanNum) {
                    //Character
                    case 3:
                        //Return title screen 2
                        gp.ui.titleScreenState = 2;
                        gp.ui.commanNum = 0;
                        break;
                    //Back
                    case 4:
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
                gp.playSE(5);
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                gp.playSE(5);
                if (gp.ui.commanNum > 1) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_A) {
                switch (gp.ui.commanNum) {
                    case 0:
                        gp.player.characterNum--;
                        if (gp.player.characterNum > 6) {
                            gp.player.characterNum = 0;
                        }
                        if (gp.player.characterNum < 0) {
                            gp.player.characterNum = 6;
                        }
                        gp.playSE(5);
                        break;
                }
            }
            if (code == KeyEvent.VK_D) {
                switch (gp.ui.commanNum) {
                    case 0:
                        gp.player.characterNum++;
                        if (gp.player.characterNum > 6) {
                            gp.player.characterNum = 0;
                        }
                        if (gp.player.characterNum < 0) {
                            gp.player.characterNum = 6;
                        }
                        gp.playSE(5);
                        break;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                switch (gp.ui.commanNum) {
                    //Back
                    case 1:
                        //Return title screen 0
                        gp.ui.titleScreenState = 1;
                        gp.ui.commanNum = 0;
                        break;
                }
            }
        } else if (gp.ui.titleScreenState == 3) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commanNum--;
                gp.playSE(5);
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 5;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commanNum++;
                gp.playSE(5);
                if (gp.ui.commanNum > 5) {
                    gp.ui.commanNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                if (gp.ui.commanNum >= 0 && gp.ui.commanNum < 5) {
                    gp.currentMap = gp.ui.commanNum+1;
                    gp.player.getPlayerImage();
                    gp.player.setDefaultValues();
                    gp.setupObject();
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                } else if (gp.ui.commanNum == 5) {
                    gp.ui.titleScreenState = 0;
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
                        gp.playMusic(0);
                        gp.ui.commanNum = 0;
                        break;
                }
            } else if (gp.ui.subState == 1) {
                switch (gp.ui.commanNum) {
                    //Yes
                    case 0:
                        gp.ui.subState = 0;
                        gp.newGame();
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

    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            gp.playSE(5);
            if (gp.ui.commanNum < 0) {
                gp.ui.commanNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            gp.playSE(5);
            if (gp.ui.commanNum > 1) {
                gp.ui.commanNum = 0;
            }

        }
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.commanNum) {
                //Retry
                case 0:
                    gp.newGame();
                    gp.gameState = gp.playState;
                    gp.ui.commanNum = 0;
                    //Play back sound
                    gp.playMusic(0);
                    break;
                //Quit
                case 1:
                    gp.gameState = gp.titleState;
                    gp.ui.commanNum = 0;
                    break;
            }
        }
    }
}
