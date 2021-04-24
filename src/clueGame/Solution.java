package clueGame;
import java.util.*;

public class Solution {
    private Card person, room, weapon;
    private Player whoSuggested;

    // Constructor for humanplayer created suggestion
    public Solution(Player player, Card person, Card room, Card weapon) {
        this.person = person;
        this.room = room;
        this.weapon = weapon;        
        whoSuggested = player;
    }

    // Constructor for ComputerPlayer created suggestion
    public Solution(Player player, Board board) {    	
        Random rand = new Random(player.getDeck().size());
        Card roomCard = null;
        String roomName = player.getLocation().getRoom().getName();
        for (Card card: board.getDeck()) {
        	if (card.getName().equals(roomName)) {
        		roomCard = card;
        	}
        }
        room = roomCard;
        ArrayList<Card> personCards = new ArrayList<Card>();
        ArrayList<Card> weaponCards = new ArrayList<Card>();
                
        for (Card card: board.getDeck()) {
        	if (card.getType().equals(CardType.PERSON) && !player.getDeck().contains(card)) {
        		personCards.add(card);
        	} else if (card.getType().equals(CardType.WEAPON) && !player.getDeck().contains(card)) {
        		weaponCards.add(card);
        	}
        }
        int size = personCards.size();        
        person = personCards.get(rand.nextInt(size));
        size = weaponCards.size();        
        weapon = weaponCards.get(rand.nextInt(size));
        whoSuggested = player;       
    }
    
    // Constructor for creating the answer to the game, where a player doesn't need to be specified
    public Solution(Card person, Card room, Card weapon) {
    	this.person = person;
    	this.room = room;
    	this.weapon = weapon;    	
    }
    
    public boolean equals(Solution solution) {
    	return (person.equals(solution.getPerson()) &&
    			room.equals(solution.getRoom()) &&
    			weapon.equals(solution.getWeapon()));
    }


/*
 * SETTERS
 */     

    public void setPerson(Card card) {
        if (card.getType().equals(CardType.PERSON)) {
            person = card;
        }
    }

    public void setRoom(Card card) {
        if (card.getType().equals(CardType.ROOM)) {
            room = card;
        }
    }

    public void setWeapon(Card card) {
        if (card.getType().equals(CardType.WEAPON)) {
            weapon = card;
        }
    }

/*
 * GETTERS
 */      
    
    public Player getWhoSuggested() {
        return whoSuggested;
    }

    public Card getPerson() {
        return person;
    }

    public Card getRoom() {
        return room;
    }

    public Card getWeapon() {
        return weapon;
    }


}
