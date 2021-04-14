import java.awt.*;

public class Card {
    private String name;
    private CardType type;
    private boolean seen;
    private Color color;

    public Card(String name, CardType type) {
        this.name = name;
        this.type = type;
        setColor();
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

    private void setColor() {
        if (type.equals(CardType.PERSON)) {
            // get color of the associated player
        } else if (type.equals(CardType.WEAPON)) {
            color = Color.RED;
        } else if (type.equals(CardType.ROOM)) {
            color = Color.CYAN;
        }
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

    public Color Color() {
        return color;
    }
    
}
