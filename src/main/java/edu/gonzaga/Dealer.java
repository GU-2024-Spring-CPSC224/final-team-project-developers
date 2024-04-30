package edu.gonzaga;

public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand(); // The dealer starts with an empty hand.
    }

    public void initialDeal(DeckOfCards deck) {
        // The dealer gets two cards initially.
        hand.addCard(deck.dealTopCard());
        hand.addCard(deck.dealTopCard());
    }

    public Hand getHand() {
        return hand; // Return the dealer's hand.
    }

    public String showInitialHand() {
        // Show only the first card of the dealer's hand and hide the second card.
        if (!hand.getCards().isEmpty()) {
            Card visibleCard = hand.getCards().get(0);
            return visibleCard.toString() + " |XX|";
        }
        return "|XX|";
    }

    public int continuePlay(DeckOfCards deck) {
        // Dealer must hit until their score is 17 or higher.
        int score = hand.calculateScore();
        while (score < 17) {
            hand.addCard(deck.dealTopCard());
            score = hand.calculateScore();
            if (score > 21) {
                break; // The dealer busts if the score is over 21.
            }
        }
        return score;
    }

    public void cleanHand() {
        hand = new Hand(); // Reset the dealer's hand for a new game.
    }


    public String getHandString() {
        // Return a string representation of the dealer's hand.
        return hand.showHand();
    }

    // Main method for testing purposes (if needed).
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        Dealer dealer = new Dealer();

        dealer.initialDeal(deck);
        System.out.println("Dealer's initial hand: " + dealer.showInitialHand());

        int finalScore = dealer.continuePlay(deck);
        System.out.println("Dealer's final hand: " + dealer.getHandString());
        if (finalScore > 21) {
            System.out.println("Dealer busted with a score of " + finalScore + ".");
        } else {
            System.out.println("Dealer stands with a score of " + finalScore + ".");
        }
    }
}
