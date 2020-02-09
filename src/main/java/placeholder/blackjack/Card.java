package placeholder.blackjack;

/**
 * A class to represent a standard playing card.
 * @author Cody
 * @version 1.0
 */
public class Card
{
    /**
     * Holds the suit of the card.
     */
    private Suit suit;
    /**
     * Holds the face value of the card.
     */
    private Value value;
    
    /**
     * Constructor; Takes in the card's suit and value as strings.
     * @param suit The suit of the card.
     * @param value The face value of the card.
     */
    public Card(String suit, String value)
    {
        this.suit = Suit.valueOf(suit);
        this.value = Value.valueOf(value);
    }
    
    /**
     * Returns the suit of the card.
     * @return The suit of the card.
     */
    public String getSuit()
    {
        return suit.toString();
    }
    
    /**
     * Returns the face value of the card.
     * @return The face value of the card.
     */
    public String getValue()
    {
        return value.toString();
    }
}
