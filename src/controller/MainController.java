package controller;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainController {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame window = new JFrame();
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setTitle("Fugitive");

                    GamePanel gamePanel = new GamePanel();
                    window.add(gamePanel);

                    window.pack();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);

                    if (gamePanel != null) {
                        gamePanel.config.loadConfig();
                        gamePanel.StartGameThread();
                    } else {
                        System.err.println("GamePanel initialization failed.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
