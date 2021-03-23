package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	
	public Card(String name, CardType type) {
		this.cardName = name;
		this.type = type;
	}
	
	public boolean equals(Card target) {
		return (this.cardName == target.getName() && this.type == target.getType());
	}
	
	public CardType getType() {
		return this.type;
	}	
	
	public String getName() {
		return this.cardName;
	}
}
