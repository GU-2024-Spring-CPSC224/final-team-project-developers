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

    public void initialDeal() {
        hand.add(deck.dealTopCard()); 
        hand.add(deck.dealTopCard()); 
    }

    public String showInitialHand() {
        if (hand.size() > 0) {
            return hand.get(0).toString() + " |XX|"; 
        }
        return "|XX|";
    }

    public int continuePlay() {
        int score = calculateScore();
        while (score < 17) {
            hand.add(deck.dealTopCard());
            score = calculateScore();
            if (score > 21) {
                break; 
            }
        }
        return score;
    }

    private int calculateScore() {
        int score = 0;
        int numberOfAces = 0;

        for (Card card : hand) {
            int rank = card.getRank();
            if (rank == Card.ACE) {
                numberOfAces++;
                score += 11; 
            } else if (rank >= Card.JACK) { 
                score += 10;
            } else {
                score += rank;
            }
        }

        while (score > 21 && numberOfAces > 0) {
            score -= 10; 
            numberOfAces--;
        }

        return score;
    }

    public String getHandString() {
        StringBuilder handBuilder = new StringBuilder();
        for (Card card : hand) {
            handBuilder.append(card.toString()).append(" "); 
        }
        return handBuilder.toString().trim(); 
    }


    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        Dealer dealer = new Dealer(deck);

        dealer.initialDeal();
        System.out.println("Dealer's initial hand: \n" + dealer.showInitialHand());

        int finalScore = dealer.continuePlay();
        System.out.println("Dealer's final hand: \n" + dealer.getHandString());
        if (finalScore > 21) {
            System.out.println("Dealer busted with a score of " + finalScore + ".");
        } else {
            System.out.println("Dealer stands with a score of " + finalScore + ".");
        }
    }
}
