package controller;

import view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MainController {

    private static ImageIcon logo;

    public static void main(String[] args) {

        logo = new ImageIcon(Objects.requireNonNull(MainController.class.getClassLoader().getResource("Fugitive.jpg")));

        EventQueue.invokeLater(() -> {
            try {
                JFrame window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("Fugitive");
                window.setIconImage(logo.getImage());

                GamePanel gamePanel = new GamePanel();
                window.add(gamePanel);

                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);

                gamePanel.config.loadConfig();
                gamePanel.StartGameThread();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
