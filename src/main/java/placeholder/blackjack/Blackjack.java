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
     * tracks whether the game loop is done.
     */
    private boolean gameIsOver = false;
    /**
     * The Deck of cards.
     */
    private Deck deck;
    
    //if the shuffling cards into the deck thing doesn't work out, then this offers a nice alternative
    //private Deck resetDeck;
    
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
    //removed to simplify game loop, will add in later
    //private int[] bets;
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
        players = new ArrayList<ArrayList<Card>>();
        for(int i = 0; i < numOfPlayers; i++)
        {
            players.add(new ArrayList<Card>());
        }
        softHand = new boolean[numOfPlayers];
        //bets = new int[numOfPlayers];
        playerStatus = new int[numOfPlayers];
        handValue = new int[numOfPlayers];
    }
    
    /**
     * The main game loop.
     */
    public void runGame()
    {
        while(!gameIsOver)
        {
            deck.shuffleDeck();
            //deal cards
            for(int i = 0; i < numOfPlayers; i++)
            {
                for(int j = 0; j < 2; j++)
                {
                    players.get(i).add(deck.drawCard());
                    System.out.println(players.get(i).get(j).getSuit() + " " + players.get(i).get(j).getValue());
                    handValue[i] += parseValue(players.get(i).get(j));
                }
                currentPlayer++;
            }

            currentPlayer = 0;
            //check for dealer blackjack
            if(handValue[numOfPlayers - 1] == 21)
            {
                System.out.println("Dealer has Blackjack, House Wins!");
                
                //Return cards to deck, zero out values, and shuffle.
                for(int i = 0; i < numOfPlayers; i++)
                {
                    while(players.get(i).size() > 0)
                    {
                        deck.placeCardOnTop(players.get(i).remove(0));
                    }
                }
                for(int i = 0; i < handValue.length; i++)
                {
                    handValue[i] = 0;
                }
                deck.shuffleDeck();
                
                continue;
            }

            //DEBUG BLOCK
            for(int i = 0; i < handValue.length; i++)
            {
                System.out.println(handValue[i]);
            }
            gameIsOver = true;
            //end debug
        }
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