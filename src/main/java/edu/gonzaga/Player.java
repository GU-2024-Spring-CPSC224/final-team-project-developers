package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Hand> hands;
    private int balance;

    public Player(int initialBalance) {
        this.hands = new ArrayList<>();
        this.balance = initialBalance;
    }

    public void initialDeal(DeckOfCards deck) {
        Hand newHand = new Hand();
        newHand.addCard(deck.dealTopCard());
        newHand.addCard(deck.dealTopCard());
        hands.add(newHand);
    }

    public void placeBet(int amount) {
        if (amount <= balance) {
            Hand hand = hands.get(0);
            hand.setBet(amount);
            balance -= amount;
            System.out.println("Bet placed: " + amount);
        } else {
            System.out.println("Not enough balance to place the bet or no hand available.");
        }
    }

    public void takeCard(DeckOfCards deck, int handIndex) {
        if (handIndex < hands.size() && !hands.get(handIndex).isStanding()) {
            hands.get(handIndex).addCard(deck.dealTopCard());
        }
    }

    public void stand(int handIndex) {
        if (handIndex < hands.size()) {
            hands.get(handIndex).stand();
        }
    }

    public int calculateScore(int handIndex) {
        if (handIndex < hands.size()) {
            return hands.get(handIndex).calculateScore();
        }
        return 0;
    }

    public String showHand(int handIndex) {
        if (handIndex < hands.size()) {
            return hands.get(handIndex).showHand();
        }
        return "No hand";
    }

    public boolean canSplit(int handIndex) {
        if (handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            return hand.getCards().size() == 2 && 
                   hand.getCards().get(0).getRank() == hand.getCards().get(1).getRank() && 
                   balance >= hand.getBet();
        }
        return false;
    }
    

    public void split(DeckOfCards deck, int handIndex) {
        if (handIndex < hands.size()) {
            Hand originalHand = hands.get(handIndex);
            if (originalHand.getCards().size() == 2 && originalHand.getCards().get(0).getRank() == originalHand.getCards().get(1).getRank() && balance >= originalHand.getBet()) {
                balance -= originalHand.getBet();
                
                Hand newHand1 = new Hand();
                newHand1.addCard(originalHand.getCards().remove(0));
                newHand1.setBet(originalHand.getBet());

                Hand newHand2 = new Hand();
                newHand2.addCard(originalHand.getCards().remove(0));
                newHand2.setBet(originalHand.getBet());

                newHand1.addCard(deck.dealTopCard());
                newHand2.addCard(deck.dealTopCard());

                hands.set(handIndex, newHand1);
                hands.add(newHand2);

                System.out.println("Hands have been split.");
            } else {
                System.out.println("Cannot split.");
            }
        }
    }

    public void winBet(int handIndex, double multiplier) {
        if (handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            int winnings = (int) (hand.getBet() * multiplier);
            balance += winnings;
            hand.setBet(0);
            System.out.println("Won " + winnings + " with hand " + (handIndex + 1));
        }
    }

    public void pushBet(int handIndex) {
        if (handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            balance += hand.getBet();
            hand.setBet(0);
            System.out.println("Push: Bet returned for hand " + (handIndex + 1));
        }
    }
    
    

    public int getBalance() {
        return balance;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void cleanHands() {
        hands.clear();
    }
}
