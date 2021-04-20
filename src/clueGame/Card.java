package clueGame;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Card extends JTextField {
    private String name;
    private CardType type;
    private boolean seen;
    private Color color;
    private Image image;
    private Border cardBorder;
    
    public Card(String name, CardType type, Color color) {
        this.name = name;
        this.type = type;
        this.color = color;
        setText(name);
      
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
    
   
  
//    @Override 
//    protected void paintComponent(Graphics g) {
//    	g.drawImage(image, 20, 20, this);
//    }
//    
//    public Image getImage() {
//    	Image image = null;
//    	try {
//    		image = ImageIO.read(this.c.getResource("icons/" + name + ".png"));
//    	} catch(IOException e) {
//    		e.printStackTrace();
//    	}
//    	return image;    
//    }
    

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
   
}
