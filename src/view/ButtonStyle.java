package view;

import javax.swing.*;
import java.awt.*;

public class ButtonStyle extends JButton
{

    public ButtonStyle(String name, int x, int y) {
        super(name);
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        setBounds(x, y, 100, 30);
        setOpaque(false);
        setBackground(Color.orange);
        setForeground(Color.BLACK);
        setBorder(null);
        setFont(buttonFont);
    }


}
