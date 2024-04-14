package edu.gonzaga;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name; 
    private List<Hand> hands;
    private int balance;

    public Player(int initialBalance, String name) {
        this.hands = new ArrayList<>();
        this.balance = initialBalance;
    }

    public void initialDeal(DeckOfCards deck) {
        Hand newHand = new Hand();

        newHand.addCard(deck.dealTopCard());
        newHand.addCard(deck.dealTopCard());

        //for testing purposes
        /****************************
         
        Card card1 = new Card(10, 1);
        Card card2 = new Card(11, 2);
        newHand.addCard(card1);
        newHand.addCard(card2);

        ****************************/ 
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

    public void placeBet(int amount, Hand hand){ 
        hand.setBet(amount + hand.getBet());
        balance -= amount;
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

    public String showHand(Hand hand) {
        
        return hand.showHand();

    }

    public boolean canDouble(int handIndex) {
        if (handIndex < hands.size()&& !hands.get(handIndex).getMoveMade() && this.balance >= hands.get(handIndex).getBet() && this.balance >= hands.get(handIndex).getBet()) {
            return true;
        }
        else{ 
            return false; 
        }
    }

    public boolean canSplit(int handIndex) {
        if (handIndex < hands.size() && this.balance >= hands.get(handIndex).getBet() && hands.size() <= 5) {
            Hand hand = hands.get(handIndex);
            int gameValue1 = getGameValue(hand.getCards().get(0));
            int gameValue2 = getGameValue(hand.getCards().get(1));
            return hand.getCards().size() == 2 && 
                   gameValue1 == gameValue2 && 
                   balance >= hand.getBet();
        }
        return false;
    }
    
    private int getGameValue(Card card) {
        int rank = card.getRank();
        if (rank == Card.JACK || rank == Card.QUEEN || rank == Card.KING) {
            return 10; 
        }
        return rank; 
    }
    
    public void split(DeckOfCards deck, int handIndex) {
        if (handIndex < hands.size()) {
            Hand originalHand = hands.get(handIndex);
    
            balance -= originalHand.getBet(); 
    
            Hand splitHand1 = new Hand();
            Hand splitHand2 = new Hand();
    
            splitHand1.addCard(originalHand.getCards().remove(0));
            splitHand1.setBet(originalHand.getBet());
    
            splitHand2.addCard(originalHand.getCards().remove(0));
            splitHand2.setBet(originalHand.getBet());
    
            hands.set(handIndex, splitHand1);
            hands.add(handIndex + 1, splitHand2); 
    
            System.out.println("Hands have been split.");
            

            System.out.println( getHandDetails(handIndex) + " " + getHandDetails(handIndex + 1));

        }
    }
    
    

    public void winBet(int handIndex, double multiplier) {
        if (handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            int winnings = (int) (hand.getBet() * multiplier);
            balance += winnings;
            hand.setBet(0);
            System.out.println("Won " + winnings/2 + " with hand " + (handIndex + 1));
        }
    }

    public void loseBet(int handIndex) {
        if (handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            hand.setBet(0);
            System.out.println("Lost bet for hand " + (handIndex + 1));
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


    public String getHandDetails(int handIndex) {
        if (handIndex >= 0 && handIndex < hands.size()) {
            Hand hand = hands.get(handIndex);
            return "Hand " + (handIndex + 1) + ": " + hand.showHand();
        }
        return "Invalid hand index";
    }
    
}
