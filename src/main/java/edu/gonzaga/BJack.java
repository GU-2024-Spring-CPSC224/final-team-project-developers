package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.event.ActionListener;


public class BJack {
    private CardSlot[] dealerCardSlots = new CardSlot[1];
    private CardSlot[] playerCardSlots = new CardSlot[1]; // Start with one card slot
    private String basicCard;
    public PlayerInfo playerInfo;
    public JLabel balanceLabel;
    private DeckOfCards deck;
    public Player player;
    private Dealer dealer;
    private JPanel playerCardArea;
    private JPanel dealerCardArea;

    JButton hitButton = new JButton("HIT");
    JButton standButton = new JButton("STAND");
    JButton doubleButton = new JButton("DOUBLE");
    JButton splitButton = new JButton("SPLIT");
    
    Integer balance = 10000; 
    Integer bet = 0;
    Integer totalBet= 0;
    Integer playedHands = 0;

    private JFrame frame;
    Color tableColor = new Color(135, 152, 122);

    public BJack() {
        basicCard = "Classic/basic.png";
        playerInfo = new PlayerInfo();
        deck = new DeckOfCards();
        dealer = new Dealer();
        player = new Player(10000, "");

        initializeFrame();

       
    }
    private void initializeFrame() {
        frame = new JFrame("Blackjack Game");
        frame.setVisible(true);
    }

    private void promptForPlayerNameAndStart() {
        String name = JOptionPane.showInputDialog(null, "Enter your name:", "Player Name", JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z0-9]*$")) {
            if (playerInfo.playerExists(name)) {
                int balance = playerInfo.balance;
                balanceLabel = new JLabel("Balance: $" + balance);
                player = new Player(balance, name);
                createAndShowGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user name", "Error", JOptionPane.ERROR_MESSAGE);
                promptForPlayerNameAndStart();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Illegal name entered. Please use only letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            promptForPlayerNameAndStart();
        }
    }

    public void promptForBetAmount() {
        String betAmountStr = JOptionPane.showInputDialog(null, "Enter the number to place a bet:", "Bet Amount", JOptionPane.PLAIN_MESSAGE);
        try {
            int betAmount = Integer.parseInt(betAmountStr);
            // Проверяем, что количество людей больше 0
            if (betAmount > 0 && betAmount <= player.getBalance()) {
                playerCardSlots[0].setMoneyText("Bet: $" + betAmountStr);
                bet = betAmount;
                balance = player.getBalance()- betAmount;
                balanceLabel.setText("Balance: $" + balance); 
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid number (greater than 0).", "Error", JOptionPane.ERROR_MESSAGE);
                // Если введено некорректное количество людей, повторно запрашиваем его
                promptForBetAmount();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            // Если введено не число, повторно запрашиваем количество людей
            promptForBetAmount();
        }
    }
    

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Blackjack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
       
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBackground(tableColor);

        customizeButton(hitButton);
        customizeButton(standButton);
        customizeButton(doubleButton);
        customizeButton(splitButton);

    
        customizeLabel(balanceLabel);

        // Add buttons to panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(balanceLabel);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);

        // Setup game panel
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(tableColor);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));

        // Setup card areas
        ImageIcon cardIcon = new ImageIcon(basicCard);
        Image image = cardIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(image);
        dealerCardSlots[0] = new CardSlot(tableColor, cardIcon, true);

        dealerCardArea = new JPanel();
        dealerCardArea.setBackground(tableColor);
        dealerCardSlots[0] = new CardSlot(tableColor, new ImageIcon(basicCard), true); // Initialize the dealer's CardSlot
        dealerCardArea.add(dealerCardSlots[0].getCardSlotPanel());

        playerCardArea = new JPanel();
        playerCardArea.setBackground(tableColor);
        playerCardArea.setLayout(new GridLayout(1, 1, 10, 0)); // Initially set for one hand
        playerCardSlots[0] = new CardSlot(tableColor, cardIcon, false); // Initialize the first CardSlot
        playerCardArea.add(playerCardSlots[0].getCardSlotPanel());

        // Add areas to game panel
        gamePanel.add(dealerCardArea, BorderLayout.NORTH);
        gamePanel.add(Box.createVerticalStrut(20), BorderLayout.CENTER);
        gamePanel.add(playerCardArea, BorderLayout.SOUTH);

        // Set frame layout and make it visible
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 100));
        button.setFont(new Font("Serif", Font.BOLD, 20));
    }

    private static void customizeLabel(JLabel label) {
        label.setPreferredSize(new Dimension(150, 100));
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setOpaque(true);
    }

    private void updatePlayerCardSlots() {
        int numHands = player.getHands().size();
        playerCardArea.setLayout(new GridLayout(1, numHands, 10, 0)); // Adjust for new number of hands
        playerCardArea.removeAll(); // Remove old card slots
        for (int i = 0; i < numHands; i++) {
            if (i >= playerCardSlots.length) {
                // If there are more hands than card slots, create new card slots
                playerCardSlots = Arrays.copyOf(playerCardSlots, numHands);
                playerCardSlots[i] = new CardSlot(tableColor, new ImageIcon(basicCard), false);
            }
            playerCardArea.add(playerCardSlots[i].getCardSlotPanel());
            // Update the cards in this hand
            Hand hand = player.getHands().get(i);
            playerCardSlots[i].clear();
            for (Card card : hand.getCards()) {
                String cardImagePath = getImagePathForCard(card);
                playerCardSlots[i].addCardImage(new ImageIcon(cardImagePath));
            }
        }
        playerCardArea.revalidate();
        playerCardArea.repaint();
    }

    private void updateDealerCardSlots(boolean playerdone) {
        int numHands = 1;
        
        // Create or clear the dealer's card area
        dealerCardArea.setLayout(new GridLayout(1, numHands, 10, 0)); // Adjust for one hand
        dealerCardArea.removeAll(); 
        
        if (dealerCardSlots.length < numHands) {
            dealerCardSlots = Arrays.copyOf(dealerCardSlots, numHands);
            dealerCardSlots[0] = new CardSlot(tableColor, new ImageIcon(basicCard), true);
        } else {
            dealerCardSlots[0].clear();
        }
    
        Hand hand = dealer.getHand(); 
        dealerCardArea.add(dealerCardSlots[0].getCardSlotPanel());
        
        if (playerdone) {
            for (Card card : hand.getCards()) {
                String cardImagePath = getImagePathForCard(card);
                dealerCardSlots[0].addCardImage(new ImageIcon(cardImagePath));
            }
        }
        else {
            String cardImagePath = getImagePathForCard(hand.getCards().get(1));
            dealerCardSlots[0].addCardImage(new ImageIcon("Classic/Card-Back-05.png"));
            dealerCardSlots[0].addCardImage(new ImageIcon(cardImagePath));
        
        }
        
        dealerCardArea.revalidate();
        dealerCardArea.repaint();
    }
    

    public void startGame() {
        deck.shuffle(); 
    
        player.initialDeal(deck);
        dealer.initialDeal(deck);

        updatePlayerCardSlots();
        updateDealerCardSlots(false);
        
        playHand(0);   
    }


    private void playDealer(){
        dealer.continuePlay(deck);
        updateDealerCardSlots(true);
    }    

    private void determineWinner(){
        int dealerScore = dealer.getHand().calculateScore();
        for (int i = 0; i < player.getHands().size(); i++) {
            int playerScore = player.calculateScore(i);
            System.out.println("\nFinal scores for hand " + (i + 1) + ":");
            System.out.println("Dealer: " + dealerScore);
            System.out.println("Player: " + playerScore);
            double multiplier = 1;
            
            if (player.getHands().get(i).ifDoubled){
                multiplier = 2;
            }
            if (player.getHands().size() ==1 && playerScore == 21 && player.getHands().get(i).getCards().size() == 2) {
                multiplier = 1.5; 
            }
    
            if (playerScore > 21) {
                System.out.println("Player busts with hand " + (i + 1));
                playerCardSlots[i].setMoneyText("You lost $" + multiplier* bet);
                
            } else if (dealerScore > 21 || playerScore > dealerScore) {
                System.out.println("Player wins with hand " + (i + 1));
                balance += player.getHands().get(i).getBet() *2 ;
                playerCardSlots[i].setMoneyText("You won $" + multiplier* bet);
            } else if (dealerScore > playerScore) {
                System.out.println("Dealer wins against hand " + (i + 1));
                playerCardSlots[i].setMoneyText("You lost $" +multiplier *  bet);
            } else {
                System.out.println("It's a tie with hand " + (i + 1));
                balance += player.getHands().get(i).getBet() ;
                playerCardSlots[i].setMoneyText("Its a tie for" +multiplier*  bet);
            }
            balanceLabel.setText("Balance $" + balance);
            playerInfo.updateBalance(player.getName(), balance);
            player.setBalance(balance);
            System.out.println(player.getName());
            playerInfo.updateBalance(player.getName(), balance);
        }
        int response = JOptionPane.showConfirmDialog(null, "Do you want to play another round?", "Play Again", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            removeActionListeners(hitButton);
            removeActionListeners(standButton);
            removeActionListeners(splitButton);
            removeActionListeners(doubleButton);
            player.cleanHands();
            dealer.cleanHand();
            bet= 0; 
            updateDealerCardSlots(true);
            updatePlayerCardSlots();
            promptForBetAmount();
            startGame();
        } else {
            frame.dispose(); 
        }
    }

    private void playHand(Integer i){ 
        Hand hand = player.getHands().get(i); 

        if (player.getHands().size() ==1 && hand.calculateScore() == 21) { 
            playDealer();
            determineWinner();
        }
        else{


            if (!hand.isStanding()){

                player.placeBet(bet); 

                hitButton.addActionListener(e -> performHit(i));
                standButton.addActionListener(e -> performStand(i));
                doubleButton.addActionListener(e -> performDouble(i));
                splitButton.addActionListener(e -> performSplit(i));
            }
        }
    }

    
    private void removeActionListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    
    private void performHit(Integer i) {
        System.out.println("Hit on hand " + i);
        Hand hand = player.getHands().get(i);
        player.takeCard(deck, i);
        updatePlayerCardSlots(); 
    
        if (hand.calculateScore() > 21) {
            JOptionPane.showMessageDialog(null, "Your hand got busted", "Bust", JOptionPane.ERROR_MESSAGE);
            hand.stand(); 
            performStand(i); 
        }
    }

    private void performStand(Integer i) {
        System.out.println("Stand on hand " + i);
        int size = player.getHands().size();
        System.out.println(i.toString() + " " + size);
    
        Hand hand = player.getHands().get(i);
        hand.showHand();
        hand.stand();
        
        removeActionListeners(hitButton);
        removeActionListeners(standButton);
        removeActionListeners(splitButton);
        removeActionListeners(doubleButton);
        playedHands++;
        if (i + 1 < player.getHands().size()) {
            playHand(i + 1);
        } else {
            playDealer();
            determineWinner();

        }
    }


    private void performSplit(Integer i) {
        if (player.canSplit(i)) { 
            player.split(deck, i); 
            player.getHands().get(i).setBet(bet);
            balance -= bet; 
            player.placeBet(bet); 
            balanceLabel.setText("Balance $" + balance);
            updatePlayerCardSlots(); 
            playerCardSlots[i+1].setMoneyText("Bet: $" +bet);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot perform split!", "Split Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performDouble(Integer i) {
        if( player.canDouble(i)){
            player.takeCard(deck, i);
            updatePlayerCardSlots(); 
            balance -= bet;
            player.placeBet(bet); 
            balanceLabel.setText("Balance $" + balance);
            updatePlayerCardSlots(); 
            playerCardSlots[i].setMoneyText("Bet: $" + 2*bet);
            player.getHands().get(i).ifDoubled =true; 
            performStand(i);
        }
        else {
            JOptionPane.showMessageDialog(null, "Cannot perform Double!", "Double Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private String getImagePathForCard(Card card) {
        return card.getCardImage();
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            JOptionPane.showMessageDialog(null, "Timer was interrupted", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public static void main(String[] args) {
        BJack bJack = new BJack();
        bJack.promptForPlayerNameAndStart();
        bJack.promptForBetAmount();
        bJack.startGame();
    }
}
