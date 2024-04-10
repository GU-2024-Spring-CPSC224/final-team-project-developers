package edu.gonzaga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeckOfCards {
    private final List<Card> deck;

    public DeckOfCards() {
        deck = new ArrayList<>();
        // Create standard deck of 52 cards
        for (int suit = Card.DIAMONDS; suit <= Card.SPADES; suit++) {
            for (int rank = Card.ACE; rank <= Card.KING; rank++) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealTopCard() {
        if (deck.isEmpty()) {
            System.out.println("No cards left in the deck.");
            return null;
        }
        return deck.remove(0);
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        System.out.println("Dealing the top card:");
        Card topCard = deck.dealTopCard();
        if (topCard != null) {
            System.out.println("Rank: " + Card.rankToString(topCard.getRank()) +
                    ", Suit: " + Card.suitToString(topCard.getSuit()));
        }
    }
}
