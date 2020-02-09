package placeholder.blackjack;

import java.util.*;
import java.io.*;

/**
 * Class to represent a deck of playing cards.
 * @author Cody
 * @version 1.0
 */

public class Deck
{
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
    
    /*public Card drawCard()
    {
        return deck.pop();
    }
    */
    /*public void shuffleDeck()
     {
         shuffling stuff
     }
     */
    /*public void placeCardOnTop()
     {
         placing stuff
     }
     */
    
    /*public void placeCardOnBottom()
     {
         placing stuff
     }
     */
    
    /*public void placeCardRandomly()
     {
         placing stuff
     }
     */
    public int getDeckSize()
    {
        return deck.size();
    }
     
}