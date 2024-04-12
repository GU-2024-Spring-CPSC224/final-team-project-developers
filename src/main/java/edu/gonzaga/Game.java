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
        scanner.nextLine(); // Очищаем буфер после ввода числа
        deck = new DeckOfCards(); // Инициализация колоды
        dealer = new Dealer(deck); // Создание дилера с доступом к колоде
        player = new Player(balance); // Создание игрока с начальным балансом
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
        dealer.initialDeal(deck); // Дилер получает две карты
        player.initialDeal(deck); // Игрок получает две карты
    }

    private void showInitialHands() {
        System.out.println("Dealer's initial hand: " + dealer.showInitialHand());
        System.out.println("Player's initial hand: " + player.showHand(0));
    }

    private void playerTurn(int handIndex) {
        while (!player.getHands().get(handIndex).isStanding()) {
            System.out.print("Choose an action for hand " + (handIndex + 1) + ": Hit (h), Stand (s), Double Down (d)" + (player.canSplit(handIndex) ? ", Split (p)" : "") + ": ");
            String action = scanner.nextLine().trim().toLowerCase();
            switch (action) {
                case "h":
                    player.takeCard(deck, handIndex);
                    System.out.println("Player's hand " + (handIndex + 1) + ": " + player.showHand(handIndex));
                    break;
                case "s":
                    player.stand(handIndex);
                    break;
                // case "d":
                //     if (player.canDoubleDown(handIndex)) {
                //         player.doubleDown(deck, handIndex);
                //    }
                //     break;
                case "p":
                    if (player.canSplit(handIndex)) {
                        player.split(deck, handIndex);
                        playerTurn(player.getHands().size() - 1); // Recursively handle the new hand
                    }
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            if (player.getHands().get(handIndex).calculateScore() > 21) {
                System.out.println("Player busted on hand " + (handIndex + 1));
                player.getHands().get(handIndex).stand();
            }
            if (player.getHands().get(handIndex).isStanding()) break;
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
    
            double multiplier = 2; // Обычный выигрыш
            if (playerScore == 21 && player.getHands().get(i).getCards().size() == 2) {
                multiplier = 1.5; // Выигрыш блэкджека
            }
    
            if (playerScore > 21) {
                System.out.println("Player busts with hand " + (i + 1));
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                System.out.println("Player wins with hand " + (i + 1));
                player.winBet(i, multiplier);
            } else if (dealerScore > playerScore) {
                System.out.println("Dealer wins against hand " + (i + 1));
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
