package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private boolean isStanding;
    private int balance; 
    private int bet;

    public Player(DeckOfCards deck, int initialBalance) {
        this.hand = new ArrayList<>();
        this.balance = initialBalance;
        this.isStanding = false;
    }

    public void takeCard(DeckOfCards deck) {
        if (!isStanding && hand.size() < 5) {  
            hand.add(deck.dealTopCard());
        }
    }

    public void stand() {
        isStanding = true;
    }

    public boolean isStanding() {
        return isStanding;
    }

    public int calculateScore() {
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

    public String showHand() {
        StringBuilder handBuilder = new StringBuilder();
        for (Card card : hand) {
            handBuilder.append(card.toString()).append(" ");
        }
        return handBuilder.toString().trim();
    }

    public List<Card> getHand() {
        return hand;
    }

    public void placeBet(int amount) {
        if (amount <= balance) {
            bet = amount;
            balance -= amount;
            System.out.println("Bet placed: " + bet);
        } else {
            System.out.println("Not enough balance to place the bet!");
        }
    }

    public void winBet(double mult) {
        balance += bet * mult; 
        bet = 0;
    }

    public void loseBet() {
        bet = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void cleanHand(){ 
        hand.clear();
        isStanding = false;
    }
}
