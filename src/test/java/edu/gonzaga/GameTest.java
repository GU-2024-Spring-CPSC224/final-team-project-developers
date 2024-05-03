package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Game game;
    private Player player;
    private Dealer dealer;
    private Hand playerHand1;
    private Hand playerHand2;
    private Hand dealerHand;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        player = new Player(1000, "Test Player");
        dealer = new Dealer();
        playerHand1 = new Hand();
        playerHand2 = new Hand();
        dealerHand = new Hand();
        List<Hand> playerHands = new ArrayList<>();
        playerHands.add(playerHand1);
        playerHands.add(playerHand2);
        player.setHands(playerHands);
        game = new Game();

        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testDetermineWinner_PlayerWins() {
        // Arrange
        playerHand1.addCard(new Card(Card.ACE, Card.HEARTS));
        playerHand1.addCard(new Card(Card.KING, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.FIVE, Card.CLUBS));
        dealerHand.addCard(new Card(Card.SIX, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 11\r\nPlayer: 21\r\nPlayer wins with hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_DealerWins() {
        // Arrange
        playerHand1.addCard(new Card(Card.SEVEN, Card.HEARTS));
        playerHand1.addCard(new Card(Card.NINE, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.KING, Card.CLUBS));
        dealerHand.addCard(new Card(Card.QUEEN, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 20\r\nPlayer: 16\r\nDealer wins against hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_Tie() {
        // Arrange
        playerHand1.addCard(new Card(Card.KING, Card.HEARTS));
        playerHand1.addCard(new Card(Card.QUEEN, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.TEN, Card.CLUBS));
        dealerHand.addCard(new Card(Card.TEN, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 20\r\nPlayer: 20\r\nIt's a tie with hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_PlayerBlackjack() {
        // Arrange
        playerHand1.addCard(new Card(Card.ACE, Card.HEARTS));
        playerHand1.addCard(new Card(Card.TEN, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.FIVE, Card.CLUBS));
        dealerHand.addCard(new Card(Card.SIX, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 11\r\nPlayer: 21\r\nPlayer wins with hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_PlayerBust() {
        // Arrange
        playerHand1.addCard(new Card(Card.KING, Card.HEARTS));
        playerHand1.addCard(new Card(Card.QUEEN, Card.DIAMONDS));
        playerHand1.addCard(new Card(Card.EIGHT, Card.CLUBS));
        dealerHand.addCard(new Card(Card.TEN, Card.CLUBS));
        dealerHand.addCard(new Card(Card.TEN, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 20\r\nPlayer: 28\r\nPlayer busts with hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_DealerBust() {
        // Arrange
        playerHand1.addCard(new Card(Card.KING, Card.HEARTS));
        playerHand1.addCard(new Card(Card.QUEEN, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.TEN, Card.CLUBS));
        dealerHand.addCard(new Card(Card.TEN, Card.SPADES));
        dealerHand.addCard(new Card(Card.EIGHT, Card.HEARTS));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 28\r\nPlayer: 20\r\nPlayer wins with hand 1\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDetermineWinner_MultipleHands() {
        // Arrange
        playerHand1.addCard(new Card(Card.KING, Card.HEARTS));
        playerHand1.addCard(new Card(Card.QUEEN, Card.DIAMONDS));
        playerHand2.addCard(new Card(Card.ACE, Card.HEARTS));
        playerHand2.addCard(new Card(Card.TEN, Card.DIAMONDS));
        dealerHand.addCard(new Card(Card.TEN, Card.CLUBS));
        dealerHand.addCard(new Card(Card.TEN, Card.SPADES));

        // Act
        game.determineWinner();

        // Assert
        String expectedOutput = "Final scores for hand 1:\r\nDealer: 20\r\nPlayer: 20\r\nIt's a tie with hand 1\r\n" +
                "Final scores"; // Add the expected output here
        assertEquals(expectedOutput, outContent.toString());
    }
}
