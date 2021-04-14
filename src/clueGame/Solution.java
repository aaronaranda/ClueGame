package clueGame;
import java.util.*;

public class Solution {
    private Card person, room, weapon;
    private Player whoSuggested;


    public Solution(Player player, Card person, Card room, Card weapon) {
        this.person = person;
        this.room = room;
        this.weapon = weapon;
        whoSuggested = player;
    }

    public Solution(Player player) {
        Random rand = new Random(player.getDeck().size());
        person = player.getPersonCards(true).get(rand.nextInt());
        room = player.getRoomCards(true).get(rand.nextInt());
        weapon = player.getWeaponCards(true).get(rand.nextInt());
        whoSuggested = player;       
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
