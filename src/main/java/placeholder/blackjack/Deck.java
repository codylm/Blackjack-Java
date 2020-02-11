package placeholder.blackjack;

import java.util.*;
import java.io.*;

/**
 * Class to represent a deck of playing cards.
 * @author Cody
 * @version 1.2
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
     * Removes and returns the top card of the deck.
     * @return The top card of the deck.
     */
    public Card drawCard()
    {
        return deck.pop();
    }
    
    /**
     * Removes and returns the bottom card of the deck.
     * @return The bottom card of the deck.
     */
    public Card takeCardFromBottom()
    {
        return deck.pollLast();
    }
    
    /**
     * Shuffles the deck into a random order.
     */
    public void shuffleDeck()
    {
        Card[] shuffledDeck = new Card[deck.size()];
        int count = 0;
        //generate first random number
        while(!deck.isEmpty())
        {
            shuffledDeck[count] = deck.pop();
            count++;
        }
        List<Card> cardList = Arrays.asList(shuffledDeck);
        
        Collections.shuffle(cardList);
        for(int i = 0; i < cardList.size(); i++)
        {
            deck.push(cardList.get(i));
        }
   }
     
    
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
     

    /**
     * Places the given card in a random point in the deck.
     * @param card The card to place randomly.
     */
    public void placeCardRandomly(Card card)
    {
        Random random = new Random();
        int randomSpot = random.nextInt(deck.size() + 1);
        Deque<Card> newDeck = new ArrayDeque<Card>();
        int count = 0;
        int deckSize = deck.size() + 1;
        while(count < deckSize)
        {
            if(count == randomSpot)
            {
                newDeck.push(card);
            }
            else
            {
                newDeck.push(deck.pollLast());
            }
            count++;
        }
        deck = newDeck;
    }
     
    /**
     * Returns the number of cards in the deck as an integer.
     * @return The number of cards in the deck.
     */
    public int getDeckSize()
    {
        return deck.size();
    }
    
    /**
     * Returns the card at the given index in the deck.
     * @param index The index of the card.
     * @return The card at the given index.
     */
    
    //I... feel like this is kinda hacked together, but it works, so...?
    public Card getCardAtIndex(int index)
    {
        Card[] array = (Card[])deck.toArray(new Card[deck.size()]);
        return array[index];
    }
}