package edu.gonzaga;

public class Game {
    private DeckOfCards deck;
    private Dealer dealer;
    private Player player; 

    public Game() {
        deck = new DeckOfCards();
        dealer = new Dealer(deck);

    }

    public void start() {
        deck.shuffle();


        dealer.initialDeal(deck);


        System.out.println("Dealer's initial hand: " + dealer.showInitialHand());




        dealer.continuePlay(deck); 

        // Вывод итогов руки дилера
        System.out.println("Dealer's final hand: " + dealer.getHandString());



    }


    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
