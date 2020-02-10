package placeholder.blackjack;

import static org.junit.Assert.*;
import org.junit.*;

public class BlackjackTest
{
    private Card testCard;
    private Deck deck;
    
    //honestly the card testing is just there because card isn't gonna do anything besides return its suite
    //and value, so i figure it's fine to have *something* in the class get tested
    @Before
    public void setUp()
    {
        testCard = new Card("CLUBS", "TWO");
        deck = new Deck();
    }
    
    @Test
    public void testGetCardSuit()
    {
        assertEquals("CLUBS", testCard.getSuit());
    }
    
    @Test
    public void testGetCardValue()
    {
        assertEquals("TWO", testCard.getValue());
    }
    
    @Test
    public void testAllCardsAddedToDeck()
    {
        assertEquals(52, deck.getDeckSize());
    }
    
    @Test
    public void testDrawCard()
    {
        Card kingHearts = new Card("HEARTS", "KING");
        boolean equals = kingHearts.isEqual(deck.drawCard());
        assertTrue(equals);
    }
    
    @Test
    public void testPlaceCardOnTop()
    {
        deck.placeCardOnTop(testCard);
        Card equalsCard = new Card("CLUBS", "TWO");
        boolean equals = equalsCard.isEqual(deck.drawCard());
        assertTrue(equals);
    }
    
    @Test
    public void testPlaceCardOnBottom()
    {
        deck.placeCardOnBottom(testCard);
        Card equalsCard = new Card("CLUBS", "TWO");
        boolean equals = equalsCard.isEqual(deck.takeCardFromBottom());
        assertTrue(equals);
    }
    
    //@Test
    public void testPlaceCardRandomly()
    {
        
    }
    
    @Test
    public void testGetNumericValueOfCard()
    {
        int testValue = testCard.parseValue();
        assertEquals(2, testValue);
    }
}
