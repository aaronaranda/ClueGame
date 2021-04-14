package clueGame;
import java.util.*;
import java.awt.*;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
        isHuman = true;
    }

    @Override
    public Card disproveSuggestion(Solution suggestion) {
        // Allow human player to select card to disprove
        Card disproval = null;
        return disproval;
    }

    @Override
    public Solution createSuggestion() {
        Solution suggestion = null;
        // Human player chooses cards
        // Create solution with 3 cards
        return suggestion;        
    }

    @Override
    public Card selectTargets() {
        // choose where to move
    	return null;
    }
}
