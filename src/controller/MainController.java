package controller;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainController
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame windown = new JFrame();
                windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                windown.setResizable(false);
                windown.setTitle("Fugitive");

                GamePanel gamePanel = new GamePanel();
                windown.add(gamePanel);

                windown.pack();

                windown.setLocationRelativeTo(null);
                windown.setVisible(true);

                gamePanel.config.loadConfig();

                gamePanel.StartGameThread();
            }
        });
    }
}
