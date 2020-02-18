package placeholder.blackjack;

/**
 * Currently being used to test the random placing and shuffling functions of Deck.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*Deck deck = new Deck();
        //Card testCard = new Card("SPADES", "TWO");
        //deck.placeCardRandomly(testCard);
        deck.shuffleDeck();
        Card displayCard = null;
        System.out.println(deck.getDeckSize());
        for(int i = 0; i < deck.getDeckSize(); i++)
        {
            displayCard = deck.getCardAtIndex(i);
            System.out.println(displayCard.getSuit() + " " + displayCard.getValue() + i);
        }*/
        
        Blackjack blackjack = new Blackjack(2);
        blackjack.runGame();
    }
}
