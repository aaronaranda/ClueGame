package clueGame;

import java.awt.Point;
import java.awt.Color;

public class HumanPlayer extends Player{

	//Constructor
	public HumanPlayer(String name) {
		super(name);
		this.isHuman = true;
	}

	@Override
	public void addSeenCard(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Solution createSuggestion(Card card5) {
		// TODO Auto-generated method stub
		return null;
	}
}
