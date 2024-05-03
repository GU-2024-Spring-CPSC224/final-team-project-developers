package edu.gonzaga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DeckOfCards {
    private final List<Card> deck;

    private static final int REBUILD_THRESHOLD = 50;

    public DeckOfCards() {
        deck = new ArrayList<>();
        buildDeck();
    }
    
    private void buildDeck() {
        deck.clear();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 4; i++) { 
                for (int suit = Card.DIAMONDS; suit <= Card.SPADES; suit++) {
                    for (int rank = Card.ACE; rank <= Card.KING; rank++) {
                        deck.add(new Card(rank, suit));
                    }
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealTopCard() {
        if (deck.isEmpty() || deck.size() < REBUILD_THRESHOLD) {
            System.out.println("Low on cards. Rebuilding and shuffling the deck.");
            buildDeck(); 
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
