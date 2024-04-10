package edu.gonzaga;

import java.util.Scanner;

public class Game {
    private DeckOfCards deck;
    private Dealer dealer;
    private Player player;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        System.out.print("Enter your balance: ");
        int balance = scanner.nextInt();
        scanner.nextLine();
        dealer = new Dealer(deck);
        player = new Player(deck, balance);
        scanner = new Scanner(System.in);
        deck = new DeckOfCards(); 
    }

    public void start() {
        while(true) {
            deck.shuffle();
            System.out.print("Place your bet: ");
            int bet = scanner.nextInt();
            scanner.nextLine();
            player.placeBet(bet);
            dealer.cleanHand();
            player.cleanHand();
            initialDeal();
            showInitialHands();
            playerTurn();
            dealerTurn();
            determineWinner();
            
            System.out.println("Your balance is now: " + player.getBalance());

            if (!askToContinue()) {
                break;
            }

        }

        scanner.close(); 
    }
    private boolean askToContinue() {
        System.out.print("Would you like to play another round? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    private void initialDeal() {
        // Initial deal for dealer and player
        dealer.initialDeal(deck);
        player.takeCard(deck);
        player.takeCard(deck);
    }

    private void showInitialHands() {
        // Show initial hands
        System.out.println("Dealer's initial hand: " + dealer.showInitialHand());
        System.out.println("Player's initial hand: " + player.showHand());
    }

    public void hit() {
        // Player takes a card
        player.takeCard(deck);
        System.out.println("Player's hand: " + player.showHand());
        if (player.calculateScore() > 21) {
            System.out.println("Player busted.");
            player.stand();
        }
    }

    public void stand() {
        // Player stands
        player.stand();
        System.out.println("Player stands.");
    }

    private void playerTurn() {
        while (!player.isStanding()) {
            System.out.print("Do you want to take another card? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if ("y".equals(input)) {
                hit();
            } else if ("n".equals(input)) {
                stand();
                break;
            } else {
                System.out.println("Invalid input. Please type 'hit' or 'stand'.");
            }
        }
    }

    private void dealerTurn() {
        // Dealer's turn
        if (!player.isStanding() || player.calculateScore() <= 21) {
            dealer.continuePlay(deck);
            System.out.println("Dealer's final hand: " + dealer.getHandString());
            if (dealer.calculateScore() > 21) {
                System.out.println("Dealer busted.");
            }
        }
    }

    private void determineWinner() {
        // Determine the winner and print the result
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();

        System.out.println("\nFinal scores:");
        System.out.println("Dealer: " + dealerScore);
        System.out.println("Player: " + playerScore);

        if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
            System.out.println("Dealer wins!");
            player.loseBet();
        } else if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("Player wins!");
            player.winBet(1);
        } else if (dealerScore == playerScore) {
            System.out.println("It's a tie!");
            player.winBet(1);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
