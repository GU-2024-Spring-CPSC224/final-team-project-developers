package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final DeckOfCards deck;
    private final List<Card> hand;

    public Dealer(DeckOfCards deck) {
        this.deck = deck;
        this.hand = new ArrayList<>();
    }

    // Calculates the best score for the hand considering the flexibility of the Ace's value.
    private int calculateScore() {
        int score = 0;
        int numberOfAces = 0;

        for (Card card : hand) {
            int rank = card.getRank();
            if (rank == Card.ACE) {
                numberOfAces += 1;
                score += 11; // Initially consider Ace as 11
            } else if (rank >= Card.JACK) { // Jack, Queen, King
                score += 10;
            } else {
                score += rank; // Numeric cards
            }
        }

        // Adjust Ace values from 11 to 1 as necessary to avoid busting
        while (score > 21 && numberOfAces > 0) {
            score -= 10; // Change one Ace from 11 to 1
            numberOfAces -= 1;
        }

        return score;
    }

    public int play() {
        while (true) {
            int score = calculateScore();
            if (score >= 17) {
                return score > 21 ? 0 : score;
            }
            hand.add(deck.dealTopCard()); // Draw a card if score is under 17
        }
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        Dealer dealer = new Dealer(deck);
        
        int finalScore = dealer.play();
        if (finalScore == 0) {
            System.out.println("Dealer busted.");
        } else {
            System.out.println("Dealer stands with a score of " + finalScore);
        }
    }
}
