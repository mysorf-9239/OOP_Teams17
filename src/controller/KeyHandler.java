package controller;

import model.entity.Player;
import view.GamePanel;
import view.UI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    GamePanel gp;
    public static boolean upPressed, downPressed, leftPressed, rightPressed;
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
            case 4:
                winState(code);
                break;
            case 5:
                guideState(code);
                break;
            case 6:
                mapState(code);
                break;
            case 7:
                introState(code);
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public void playState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
            gp.stopMusic();
            gp.ui.commanNum = 5;
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

                if (tileAddress >= 0 && gp.player.hasAxe > 0) {

                    int tileCol = tileAddress % GamePanel.maxWorldRow;
                    int tileRow = tileAddress / GamePanel.maxWorldRow;

                    gp.player.hasAxe -= 1;
                    gp.tileManager.cutTree(tileCol, tileRow);
                }
            }

            if (code == KeyEvent.VK_M) {
                gp.gameState = gp.mapState;
            }


            //DeBug
            if (code == KeyEvent.VK_T) {
                showDebugText = !showDebugText;
            }
            //Load map
            if (code == KeyEvent.VK_R) {
                gp.tileManager.loadMap(GamePanel.currentMap);
            }
        }
    }

    public void titleState(int code) {
        if (gp.ui.titleScreenState == 0) {

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
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                switch (gp.ui.commanNum) {
                    //New game
                    case 0:
                        if (gp.gameMode == 0) {
                            GamePanel.currentMap = 0;
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
                            GamePanel.currentMap = 0;
                            gp.player.setDefaultValues();
                            gp.newGame();
                        } else {
                            gp.player.getPlayerImage();
                            gp.player.setDefaultValues();
                            gp.tileManager.loadMap(GamePanel.currentMap);
                            gp.setupObject();
                        }
                        gp.saveLoad.load();
                        gp.player.getPlayerImage();
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        gp.ui.commanNum = 0;
                        break;
                    //Setting
                    case 2:
                        gp.ui.titleScreenState = 1;
                        gp.ui.commanNum = 0;
                        break;
                    //Guide
                    case 3:
                        gp.gameState = gp.guideState;
                        gp.ui.commanNum = 0;
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
                        gp.ui.commanNum = 2;
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
                if (gp.ui.commanNum == 0) {
                    Player.characterNum--;
                    if (Player.characterNum > 6) {
                        Player.characterNum = 0;
                    }
                    if (Player.characterNum < 0) {
                        Player.characterNum = 6;
                    }
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_D) {
                if (gp.ui.commanNum == 0) {
                    Player.characterNum++;
                    if (Player.characterNum > 6) {
                        Player.characterNum = 0;
                    }
                    if (Player.characterNum < 0) {
                        Player.characterNum = 6;
                    }
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                //Back
                if (gp.ui.commanNum == 1) {
                    //Return title screen 0
                    gp.ui.titleScreenState = 1;
                    gp.ui.commanNum = 3;
                }
            }
        }
        else if (gp.ui.titleScreenState == 3) {

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
                if (gp.ui.commanNum == 0) {
                    UI.selectMap--;
                    if (UI.selectMap > 20) {
                        UI.selectMap = 1;
                    }
                    if (UI.selectMap < 1) {
                        UI.selectMap = 20;
                    }
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_D) {
                if (gp.ui.commanNum == 0) {
                    UI.selectMap++;
                    if (UI.selectMap > 20) {
                        UI.selectMap = 1;
                    }
                    if (UI.selectMap < 1) {
                        UI.selectMap = 20;
                    }
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(5);
                switch (gp.ui.commanNum) {
                    //Select Map
                    case 0:
                        if (UI.selectMap <= GamePanel.highestMap) {
                            GamePanel.currentMap = UI.selectMap;
                            gp.player.getPlayerImage();
                            gp.player.setDefaultValues();
                            gp.tileManager.loadMap(GamePanel.currentMap);
                            gp.setupObject();
                            gp.gameState = gp.playState;
                            gp.playMusic(0);
                        } else {
                            gp.ui.titleScreenState = 4;
                        }
                        gp.ui.commanNum = 0;
                        break;
                    //Back
                    case 1:
                        //Return title screen 0
                        gp.ui.titleScreenState = 0;
                        gp.ui.commanNum = 0;
                        break;
                }
            }
        }
        else if (gp.ui.titleScreenState == 4) {

            if (code == KeyEvent.VK_ENTER) {
                //Back
                gp.ui.titleScreenState = 3;
                gp.ui.commanNum = 0;
                gp.playSE(5);
            }
        }
    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            gp.playSE(5);
            if (gp.ui.subState == 0) {
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 5;
                }
            } else if (gp.ui.subState == 1) {
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 1;
                }
            } else if (gp.ui.subState == 2) {
                if (gp.ui.commanNum != 0) {
                    gp.ui.commanNum = 0;
                }
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            gp.playSE(5);
            if (gp.ui.subState == 0) {
                if (gp.ui.commanNum > 5) {
                    gp.ui.commanNum = 0;
                }
            } else if (gp.ui.subState == 1) {
                if (gp.ui.commanNum > 1) {
                    gp.ui.commanNum = 0;
                }
            } else if (gp.ui.subState == 2) {
                if (gp.ui.commanNum != 0) {
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
                    //Control
                    case 2:
                        gp.ui.subState = 2;
                        gp.ui.commanNum = 0;
                        break;
                    //EndGame
                    case 3:
                        gp.ui.subState = 1;
                        gp.ui.commanNum = 0;
                        break;
                    //Retry
                    case 4:
                        if (GamePanel.currentMap == 0) {
                            gp.player.setDefaultValues();
                            gp.newGame();
                        } else if (GamePanel.currentMap > 0) {
                            gp.player.hasAxe = 1;
                            gp.tileManager.loadMap(GamePanel.currentMap);
                            gp.player.setDefaultValues();
                            gp.setupObject();
                        }
                        gp.gameState = gp.playState;
                        gp.ui.commanNum = 0;
                        //Play back sound
                        gp.playMusic(0);
                        break;
                    //Back
                    case 5:
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                        gp.ui.commanNum = 0;
                        break;
                }
            } else if (gp.ui.subState == 1) {
                switch (gp.ui.commanNum) {
                    //Yes
                    case 0:
                        gp.saveLoad.save();
                        gp.ui.subState = 0;
                        gp.ui.titleScreenState = 0;
                        gp.newGame();
                        gp.gameState = gp.titleState;
                        break;
                    //No
                    case 1:
                        gp.ui.subState = 0;
                        gp.ui.commanNum = 2;
                        break;
                }
            } else if (gp.ui.subState == 2) {
                //Back
                if (gp.ui.commanNum == 0) {
                    gp.ui.subState = 0;
                }
            }
            gp.playSE(5);
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

            int disScore = (GamePanel.maxWorldRow - 11 - Player.furthestY /gp.titleSize)* GamePanel.DISTANCE_REWARD;
            int Score = GamePanel.totalScore + disScore;

            if (Score > GamePanel.highestScore) {
                GamePanel.highestScore = Score;
            }
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

    private void winState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            gp.playSE(5);
            if (gp.ui.commanNum < 0) {
                gp.ui.commanNum = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            gp.playSE(5);
            if (gp.ui.commanNum > 2) {
                gp.ui.commanNum = 0;
            }

        }
        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.commanNum) {
                //Next Map
                case 0:
                    if (GamePanel.currentMap > 0 && GamePanel.currentMap < 20) {
                        GamePanel.currentMap += 1;
                        gp.player.hasAxe = 1;
                        gp.tileManager.loadMap(GamePanel.currentMap);
                        gp.player.setDefaultValues();
                        gp.setupObject();
                        if (GamePanel.currentMap > GamePanel.highestMap) {
                            GamePanel.highestMap = GamePanel.currentMap;
                        }
                    }
                    gp.gameState = gp.playState;
                    gp.ui.commanNum = 0;
                    gp.config.saveConfig();
                    //Play back sound
                    gp.playMusic(0);
                    break;
                //Retry
                case 1:
                    if (GamePanel.currentMap > 0 && GamePanel.currentMap < 6) {
                        gp.player.hasAxe = 1;
                        gp.tileManager.loadMap(GamePanel.currentMap);
                        gp.player.setDefaultValues();
                        gp.deletedObject();
                        gp.setupObject();
                    }
                    gp.gameState = gp.playState;
                    gp.ui.commanNum = 0;
                    //Play back sound
                    gp.playMusic(0);
                    break;

                //Quit
                case 2:
                    gp.gameState = gp.titleState;
                    gp.ui.commanNum = 0;
                    break;
            }
        }
    }

    private void guideState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commanNum--;
            gp.playSE(5);
            if (gp.ui.guideState == 0) {
                if (gp.ui.commanNum < 0) {
                    gp.ui.commanNum = 2;
                }
            } else if (gp.ui.guideState == 1) {
                if (gp.ui.commanNum != 0) {
                    gp.ui.commanNum = 0;
                }
            } else if (gp.ui.guideState == 2) {
                if (gp.ui.commanNum != 0) {
                    gp.ui.commanNum = 0;
                }
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commanNum++;
            gp.playSE(5);if (gp.ui.guideState == 0) {
                if (gp.ui.commanNum > 2) {
                gp.ui.commanNum = 0;
                }
            } else if (gp.ui.guideState == 1) {
                if (gp.ui.commanNum != 0) {
                    gp.ui.commanNum = 0;
                }
            } else if (gp.ui.guideState == 2) {
                if (gp.ui.commanNum != 0) {
                    gp.ui.commanNum = 0;
                }
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.guideState == 0) {
                switch (gp.ui.commanNum) {
                    //Controls
                    case 0:
                        gp.ui.guideState = 1;
                        break;
                    //Objects
                    case 1:
                        gp.ui.guideState = 2;
                        gp.ui.commanNum = 0;
                        break;
                    //Back
                    case 2:
                        gp.gameState = gp.titleState;
                        gp.ui.commanNum = 3;
                        break;
                }
            } else if (gp.ui.guideState == 1) {
                //Back
                if (gp.ui.commanNum == 0) {
                    gp.ui.guideState = 0;
                    gp.ui.commanNum = 2;
                }
            } else if (gp.ui.guideState == 2) {
                //Back
                if (gp.ui.commanNum == 0) {
                    gp.ui.guideState = 0;
                    gp.ui.commanNum = 2;
                }
            }
            gp.playSE(5);
        }
    }

    private void mapState(int code) {

        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    private void introState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            switch (gp.ui.introState) {
                case 0:
                    gp.ui.introState = 1;
                    break;
                case 1:
                    gp.ui.introState = 2;
                    break;
                case 2:
                    gp.gameState = gp.titleState;
                    Config.hasSeenIntro = true;
                    gp.config.saveConfig();
                    break;
            }
        }
    }
}
