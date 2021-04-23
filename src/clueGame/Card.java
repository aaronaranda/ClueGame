package clueGame;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.border.Border;

public class Card extends JTextField {
    private String name;
    private CardType type;
    private boolean seen;
    private Color color;
    private ImageIcon icon;
    private JLabel iconLabel;
    
    
    public Card(String name, CardType type, Color color) {
        this.name = name;
        this.type = type;
        this.color = color;
        setText(name);     
        this.setEditable(false);
        this.setSize(new Dimension(150, 50));
        if (type.equals(CardType.WEAPON)) {        	// Weapon cards get an icon 
        	icon = new ImageIcon("./icons/" + name + ".png");
        	Image im = icon.getImage();
        	Image newImage = im.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // must be rescaled
        	icon = new ImageIcon(newImage);
        	iconLabel = new JLabel();
        	iconLabel.setIcon(icon);
        	iconLabel.setSize(20, 20);
            add(iconLabel);
 
        }              
    }

    public boolean equals(Card target) {
        return (name.equals(target.getName()) && 
                type.equals(target.getType()));
    }

/*
 * SETTERS
 */ 

    public void seeCard() {
        seen = true;
    }

/*
 * GETTERS
 */ 

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;        
    }

    public boolean getSeen() {
        return seen;
    }

    public Color getColor() {
        return color;
    }
   
    public JLabel getLabel() {
    	return iconLabel;
    }
   
}
