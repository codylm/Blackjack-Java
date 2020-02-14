package placeholder.blackjack;

import java.util.*;

/**
 * A class to manage a game of Blackjack.
 * @author Cody
 * @version 1.0
 */

public class Blackjack
{
    /**
     * Constant marking the point where the AI stands, and under which the dealer must hit.
     */
    private final int standPoint = 17;
    /**
     * Constant marking the point where the players and dealer bust.
     */
    private final int cap = 21;
    /**
     * Index tracking the current player.
     */
    private int currentPlayer = 0;
    /**
     * The number of players.
     */
    private int numOfPlayers;
    /**
     * The Deck of cards.
     */
    private Deck deck;
    
    /**
     * Holds the hands of the players.
     */
    private List<ArrayList<Card>> players;
    /**
     * Tracks whether each player's value is "soft", or can be reduced because of an ace if they bust.
     */
    private boolean[] softHand;
    /**
     * Tracks the bets of the players.
     */
    private int[] bets;
    /**
     * Tracks whether the players are open, standing, or busted.
     */
    private int[] playerStatus;
    /**
     * Tracks the numerical value of the cards in their hands.
     */
    private int[] handValue;
    
    /**
     * Constructor; Initializes all variables.
     * @param numPlayers The number of players in the game of Blackjack.
     */
    public Blackjack(int numPlayers)
    {
        numOfPlayers = numPlayers;
        deck = new Deck();
        players = new ArrayList<ArrayList<Card>>(numOfPlayers);
        softHand = new boolean[numOfPlayers];
        bets = new int[numOfPlayers];
        playerStatus = new int[numOfPlayers];
        handValue = new int[numOfPlayers];
    }
    
    /**
     * The main game loop.
     */
    public void runGame()
    {
        
    }
    
    /**
     * Parses the face value of cards relative to the rules of Blackjack.
     * @param card The card to be parse.
     * @return The integer value of the card's face value.
     */
    private int parseValue(Card card)
    {
        switch(Value.valueOf(card.getValue()))
        {
            case ACE:
                if(handValue[currentPlayer] > 10) return 1;
                else
                {
                    softHand[currentPlayer] = true;
                    return 11;
                }
            case JACK:
                return 10;
            case QUEEN:
                return 10;
            case KING:
                return 10;
            default:
                return card.parseValue();
        }
    }
}