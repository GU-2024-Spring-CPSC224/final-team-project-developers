package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private int bet;
    private boolean isStanding;
    private boolean madeMove; 
    public boolean ifDoubled = false; 

    public Hand() {
        this.cards = new ArrayList<>();
        this.bet = 0;
        this.isStanding = false;
        this.madeMove = false;
    }

    

    public void addCard(Card card) {
        if (!isStanding) {
            cards.add(card);
        }
    }

    public void madeMove(){
        madeMove = true;
    }

    public boolean getMoveMade(){
        return madeMove;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isStanding() {
        return isStanding;
    }

    public void stand() {
        isStanding = true;
    }

    public int calculateScore() {
        int score = 0;
        int numberOfAces = 0;
        
        for (Card card : cards) {
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

    public String showHand() {
        StringBuilder handBuilder = new StringBuilder();
        for (Card card : cards) {
            handBuilder.append(card.toString()).append(" ");
        }
        return handBuilder.toString().trim();
    }

    public List<Card> getCards() {
        return cards;
    }
}
