package clueGame;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

public class HumanPlayer extends Player{

	//Constructor
	public HumanPlayer(String name) {
		super(name);
		this.isHuman = true;
	}

	@Override
	public Card disproveSuggestion(Solution suggestion) {
		// Must allow human player to choose a card to disprove suggestion
		return null;
	}

	@Override
	public Solution createSuggestion(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card selectTargets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> getSeenRoomCards() {
		// TODO Auto-generated method stub
		return null;
	}
}
