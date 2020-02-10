package placeholder.blackjack;

/**
 * A class to represent a standard playing card.
 * @author Cody
 * @version 1.1
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
    
    /**
     * Checks if the given card has an equal suit and face value to this card.
     * @param card The card to check for equality.
     * @return True if the card has the same suit and face value, false otherwise.
     */
    public boolean isEqual(Card card)
    {
        if(this.getSuit() == card.getSuit() && this.getValue() == card.getValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Parses the face value of the card into an integer value ranging from 1 for ace to 13 for king.
     * @return An integer representation of the card's face value.
     */
    public int parseValue()
    {
        switch(Value.valueOf(this.getValue()))
        {
            case ACE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case JACK:
                return 11;
            case QUEEN:
                return 12;
            case KING:
                return 13;
            default:
                return 0;
        }
    }
}
