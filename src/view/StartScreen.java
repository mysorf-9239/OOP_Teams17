package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StartScreen extends JFrame
{
    private BufferedImage backgroundImage;


    public StartScreen()
    {
        initGUI();

        setTitle("StartScreen");
        setResizable(false);
        setSize(1168, 780);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void initGUI() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/image/StartScreen_gif.gif"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setLayout(null);

        JLabel label1 = new JLabel("Fugitive");
        label1.setBounds(220, 100, 400, 150);
        label1.setFont(new Font("Arial", Font.BOLD, 60));
        label1.setForeground(Color.pink);

        JLabel label2 = new JLabel("version 1.0.0");
        label2.setBounds(1050, 706, 70, 30);
        label2.setFont(new Font("Arial", Font.BOLD, 10));
        label2.setForeground(Color.darkGray);

        ButtonStyle button1 = new ButtonStyle("Continue", 800, 300);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ButtonStyle button2 = new ButtonStyle("Start", 800, 350);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ButtonStyle button3 = new ButtonStyle("Mode", 800, 400);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ButtonStyle button4 = new ButtonStyle("Setting", 800, 450);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ButtonStyle button5 = new ButtonStyle("Exit", 800, 500);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);

        add(contentPane);
        contentPane.add(label1);
        contentPane.add(label2);
    }
}
