package placeholder.blackjack;

import java.util.*;
import java.io.*;

/**
 * Class to represent a deck of playing cards.
 * @author Cody
 * @version 1.1
 */

public class Deck
{
    /**
     * The data structure representing a deck of standard playing cards.
     */
    private Deque<Card> deck;
    
    /**
     * Constructor; Instantiates the deck and populates it with cards.
     */
    public Deck()
    {
        deck = new ArrayDeque<Card>();
        BufferedReader reader = null;
        String line = null;
        try
        {
            reader = new BufferedReader(new FileReader("playingcards.txt"));
            String[] cardInfo = new String[2];
            while((line = reader.readLine()) != null)
            {
                cardInfo = line.split(" ");
                deck.push(new Card(cardInfo[0], cardInfo[1]));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // Close the writer regardless of what happens...
                reader.close();
            }
            catch (Exception e){}
        }
        
    }
    
    /**
     * Returns the top card of the deck.
     * @return The top card of the deck.
     */
    public Card drawCard()
    {
        return deck.pop();
    }
    
    /**
     * Returns the card on the bottom of the deck.
     * @return The card on the bottom of the deck.
     */
    public Card takeCardFromBottom()
    {
        return deck.pollLast();
    }
    /*public void shuffleDeck()
     {
         I think I'll need to store the size (minus one?) into an index and loop through
         the deck, randoming a number to place the card into (maybe a list or something
         to store the indexes that already have cards in them?)
     }
     */
    /**
     * Places the given card on top of the deck.
     * @param card The card to be placed.
     */
    public void placeCardOnTop(Card card)
    {
        deck.push(card);
    }
    
    /**
     * Places the given card on the bottom of the deck.
     * @param card The card to be placed.
     */
    public void placeCardOnBottom(Card card)
    {
         deck.add(card);
    }
     
    
    /*public void placeCardRandomly(Card card)
     {
         placing stuff
         i think... I'm gonna have to random a number of size (not minus one because
         i'm upping the end size by one) then looping through the deck and placing the deck
         into a new deque, checkign to place the required card when I get to the right index.
     }
     */
    /**
     * Returns the number of cards in the deck as an integer.
     * @return The number of cards in the deck.
     */
    public int getDeckSize()
    {
        return deck.size();
    }
     
}