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
        deck = new DeckOfCards();
        dealer = new Dealer(deck);
        player = new Player(balance);
    }

    public void start() {
        while(true) {
            deck.shuffle();
            dealer.cleanHand();
            player.cleanHands();
            initialDeal();
            System.out.print("Place your bet: ");
            int bet = scanner.nextInt();
            scanner.nextLine();
            player.placeBet(bet);
            
           

            showInitialHands();
            for (int i = 0; i < player.getHands().size(); i++) {
                playerTurn(i);
            }
            dealerTurn();
            determineWinner();
            
            System.out.println("Your balance is now: " + player.getBalance());

            if (!askToContinue()) {
                System.out.println("Game over. Your final balance is: " + player.getBalance());
                break;
            }
        }

        scanner.close();
    }

    private void initialDeal() {
        dealer.initialDeal(deck); 
        player.initialDeal(deck); 
    }

    private void showInitialHands() {
        System.out.println("Dealer's initial hand: " + dealer.showInitialHand());
        System.out.println("Player's initial hand: " + player.showHand(0));
    }

    private void playerTurn(int handIndex) {
        Hand hand = player.getHands().get(handIndex);
        while (!hand.isStanding() && hand.calculateScore() <= 21) {
            String helperString = "";
            if (hand.getCards().size() > 1) {
                if (player.canSplit(handIndex)) {
                    helperString += ", Split (p)";
                }
            }
            System.out.print("Choose an action for hand " + (handIndex + 1) + ": Hit (h), Stand (s)" + helperString + ": ");
            String action = scanner.nextLine().trim().toLowerCase();
    
            switch (action) {
                case "h":
                    player.takeCard(deck, handIndex);
                    System.out.println("Player's hand " + (handIndex + 1) + ": " + player.showHand(handIndex));
                    break;
                case "s":
                    hand.stand();
                    break;
                case "p":
                    if (player.canSplit(handIndex)) {
                        player.split(deck, handIndex);
                        handlePostSplit(handIndex); 
                        return; 
                    }
                    break;
                default:
                    System.out.println("Invalid input. Please choose a valid action.");
                    continue;
            }
    
            if (hand.calculateScore() > 21) {
                System.out.println("Player busted on hand " + (handIndex + 1));
                hand.stand(); 
            }
        }
    }


    private void handlePostSplit(int splitIndex) {
        // Начинаем обработку с текущего индекса руки, который был разделен
        for (int newIndex = splitIndex; newIndex < player.getHands().size(); newIndex++) {
            playerTurn(newIndex);
        }
    }
    
    
    
    

    private void dealerTurn() {
        dealer.continuePlay(deck);
        System.out.println("Dealer's final hand: " + dealer.getHandString());
    }

    private void determineWinner() {
        int dealerScore = dealer.calculateScore();
        for (int i = 0; i < player.getHands().size(); i++) {
            int playerScore = player.calculateScore(i);
            System.out.println("\nFinal scores for hand " + (i + 1) + ":");
            System.out.println("Dealer: " + dealerScore);
            System.out.println("Player: " + playerScore);
    
            double multiplier = 2; 
            if (playerScore == 21 && player.getHands().get(i).getCards().size() == 2) {
                multiplier = 1.5; 
            }
    
            if (playerScore > 21) {
                System.out.println("Player busts with hand " + (i + 1));
                player.loseBet(i);
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                System.out.println("Player wins with hand " + (i + 1));
                player.winBet(i, multiplier);
            } else if (dealerScore > playerScore) {
                System.out.println("Dealer wins against hand " + (i + 1));
                player.loseBet(i);
            } else {
                System.out.println("It's a tie with hand " + (i + 1));
                player.pushBet(i);
            }
        }
    }
    

    private boolean askToContinue() {
        System.out.print("Would you like to play another round? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
