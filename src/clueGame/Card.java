package clueGame;

public class Card {
	private String cardName;
	private CardType type;
    private boolean seen;
	
	public Card(String name, CardType type) {
		this.cardName = name;
		this.type = type;
	}
	
	public boolean equals(Card target) {
		return (this.cardName == target.getName() && this.type == target.getType());
	}

    public void setSeen() {
        this.seen = true;
    }

    public boolean getSeen() {
        return this.seen;
    }
	
	public CardType getType() {
		return this.type;
	}	
	
	public String getName() {
		return this.cardName;
	}
}
