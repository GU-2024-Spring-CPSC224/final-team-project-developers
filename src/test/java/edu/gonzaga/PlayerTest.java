package edu.gonzaga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player player;
    private Hand hand1;
    private Hand hand2;

    @BeforeEach
    public void setUp() {
        player = new Player(1000, "Test Player"); // Initial balance of 1000
        hand1 = new Hand();
        hand1.setBet(100); // Bet of 100
        hand2 = new Hand();
        hand2.setBet(200); // Bet of 200
        List<Hand> hands = new ArrayList<>();
        hands.add(hand1);
        hands.add(hand2);
        player.setHands(hands);
    }

    @Test
    public void testCanDouble_WithSufficientBalance_NoMoveMade() {
        // Arrange
        int handIndex = 0;

        // Act
        boolean canDouble = player.canDouble(handIndex);

        // Assert
        assertTrue(canDouble);
    }

    @Test
    public void testCanDouble_WithInsufficientBalance() {
        // Arrange
        player.setBalance(50); // Set balance to 50
        int handIndex = 1;

        // Act
        boolean canDouble = player.canDouble(handIndex);

        // Assert
        assertFalse(canDouble);
    }

    @Test
    public void testCanSplit_ValidConditions() {
        // Arrange
        int handIndex = 0;
        hand1.setBet(100); // Set bet to 100
        player.setBalance(200); // Set balance to 200

        // Create a hand with two cards of the same rank
        Card card1 = new Card(Card.KING, Card.HEARTS);
        Card card2 = new Card(Card.KING, Card.DIAMONDS);
        hand1.addCard(card1);
        hand1.addCard(card2);

        // Act
        boolean canSplit = player.canSplit(handIndex);

        // Assert
        assertTrue(canSplit);
    }

    @Test
    public void testCanSplit_InvalidHandIndex() {
        // Arrange
        int handIndex = 2; // Invalid hand index

        // Act
        boolean canSplit = player.canSplit(handIndex);

        // Assert
        assertFalse(canSplit);
    }

    @Test
    public void testCanSplit_InsufficientBalance() {
        // Arrange
        int handIndex = 0;
        hand1.setBet(200); // Set bet to 200
        player.setBalance(100); // Set balance to 100

        // Create a hand with two cards of the same rank
        Card card1 = new Card(Card.KING, Card.HEARTS);
        Card card2 = new Card(Card.KING, Card.DIAMONDS);
        hand1.addCard(card1);
        hand1.addCard(card2);

        // Act
        boolean canSplit = player.canSplit(handIndex);

        // Assert
        assertFalse(canSplit);
    }

    @Test
    public void testCanSplit_HandSizeNotTwo() {
        // Arrange
        int handIndex = 0;
        hand1.setBet(100); // Set bet to 100
        player.setBalance(200); // Set balance to 200

        // Create a hand with three cards
        Card card1 = new Card(Card.KING, Card.HEARTS);
        Card card2 = new Card(Card.KING, Card.DIAMONDS);
        Card card3 = new Card(Card.QUEEN, Card.SPADES);
        hand1.addCard(card1);
        hand1.addCard(card2);
        hand1.addCard(card3);

        // Act
        boolean canSplit = player.canSplit(handIndex);

        // Assert
        assertFalse(canSplit);
    }

    @Test
    public void testCanSplit_DifferentRanks() {
        // Arrange
        int handIndex = 0;
        hand1.setBet(100); // Set bet to 100
        player.setBalance(200); // Set balance to 200

        // Create a hand with two cards of different ranks
        Card card1 = new Card(Card.KING, Card.HEARTS);
        Card card2 = new Card(Card.QUEEN, Card.DIAMONDS);
        hand1.addCard(card1);
        hand1.addCard(card2);

        // Act
        boolean canSplit = player.canSplit(handIndex);

        // Assert
        assertFalse(canSplit);
    }

}
