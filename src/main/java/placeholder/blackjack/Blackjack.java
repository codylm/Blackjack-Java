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
                    Card card = deck.drawCard();
                    players.get(i).add(card);
                    if(card.getValue() == "ACE") softHand[i] = true;
                    System.out.println(card.getSuit() + " " + card.getValue());
                    handValue[i] += parseValue(card);
                }
                System.out.println(+ handValue[i]);
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

            Scanner move = new Scanner(System.in); //had to pull this out of the loop entirely because of scope errors
            
            //do-while is necessary for one player vs dealer setup
            do
            {
                //If blackjack, end loop.
                if(handValue[currentPlayer] == 21)
                {
                    System.out.println("Player " + (currentPlayer + 1) + " has Blackjack!");
                    playerStatus[currentPlayer] = 1;
                }
                else
                {
                    while(playerStatus[currentPlayer] == 0)
                    {
                        //Get player input
                        System.out.print("Player " + (currentPlayer + 1) + ", please enter HIT or STAND to indicate your move: ");
                        String input = move.next(); //the program isn't liking this for some reason, says there's no such element
                        switch(input)
                        {
                            case "HIT":
                                //If hit, draw card, show player card, add card to hand and increase hand value
                                Card card = deck.drawCard();
                                if(handValue[currentPlayer] < 11 && card.getValue() == "ACE") softHand[currentPlayer] = true;
                                System.out.print(card.getSuit() + " " + card.getValue());
                                players.get(currentPlayer).add(card);
                                handValue[currentPlayer] += parseValue(card);
                                //if player busts, check for a soft hand and try to save the day
                                if(handValue[currentPlayer] > 21)
                                {
                                    if(softHand[currentPlayer] == true)
                                    {
                                        handValue[currentPlayer] -= 10;
                                        softHand[currentPlayer] = false;
                                        if(handValue[currentPlayer] > 21)
                                        {
                                            System.out.println("BUST!");
                                            playerStatus[currentPlayer] = 2;
                                        }
                                        else
                                        {
                                            System.out.print(" " + handValue[currentPlayer] + "\n");
                                        }
                                    }
                                    else
                                    {
                                        System.out.print(" " + handValue[currentPlayer] + "\n");
                                        System.out.println("BUST!");
                                        playerStatus[currentPlayer] = 2;
                                    }
                                }
                                else
                                {
                                    
                                    System.out.print(" " + handValue[currentPlayer] + "\n");
                                }
                                break;
                            case "STAND":
                                playerStatus[currentPlayer] = 1;
                                break;
                            default:
                                System.out.println("Please enter a valid command");
                        }
                    }
                }
                currentPlayer++;
            }
            while(currentPlayer < numOfPlayers - 1);

            move.close(); //had to pull this out of the loop entirely because of scope errors
            
            //DEBUG BLOCK
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