package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	
	public Card(String name, CardType type) {
		this.cardName = name;
		this.type = type;
	}
	
	
	public boolean equals(Card target) {
		// TODO
		return true;
	}
	
	public CardType getType() {
		return this.type;
	}
	
}
