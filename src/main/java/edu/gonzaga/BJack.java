package edu.gonzaga;

import javax.swing.*;
import java.awt.*;

public class BJack {

    private CardSlot[] dealerCardSlots = new CardSlot[1];
    private CardSlot[] playerCardSlots = new CardSlot[4];
    
    private String basicCard ; 

    private PlayerInfo playerInfo;
    private JLabel balanceLabel;

    private DeckOfCards deck;
    private Player player ;
    private Dealer dealer;


    public BJack(){
        basicCard = "Classic/basic.png"; 
        playerInfo = new PlayerInfo();
        deck = new DeckOfCards();
        dealer = new Dealer(deck);
    }

    // In the BJack class
    private void promptForPlayerNameAndStart() {
        String name = JOptionPane.showInputDialog(null, "Enter your name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
        
        if (name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z0-9]*$")) {
            if (playerInfo.playerExists(name)) {
                int balance = playerInfo.getBalance(name);
                balanceLabel = new JLabel("Balance: $" + balance);
                player = new Player(balance, name);
                createAndShowGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user name", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Illegal name entered. Please use only letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    


    private void createAndShowGUI() {
        JFrame frame = new JFrame("Blackjack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600); 

        Color tableColor = new Color(135, 152, 122);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonPanel.setBackground(tableColor); 

        JButton hitButton = new JButton("HIT");
        JButton standButton = new JButton("STAND");
        JButton doubleButton = new JButton("DOUBLE");
        JButton splitButton = new JButton("SPLIT");
        customizeButton(hitButton);
        customizeButton(standButton);
        customizeButton(doubleButton);
        customizeButton(splitButton);

        JLabel balanceLabel = this.balanceLabel;
        customizeLabel(balanceLabel);

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(balanceLabel);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);
        

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(tableColor);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));

  
        ImageIcon cardIcon = new ImageIcon(basicCard);
        Image image = cardIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(image);
    
        JPanel dealerCardArea = new JPanel();
        dealerCardArea.setBackground(tableColor);
        dealerCardSlots[0] = new CardSlot(tableColor, cardIcon);
        dealerCardArea.add(dealerCardSlots[0].getCardSlotPanel());
    
        JPanel playerCardArea = new JPanel();
        playerCardArea.setBackground(tableColor);
        playerCardArea.setLayout(new GridLayout(1, 4, 10, 0));
        for (int i = 0; i < playerCardSlots.length; i++) {
            playerCardSlots[i] = new CardSlot(tableColor, cardIcon);
            playerCardArea.add(playerCardSlots[i].getCardSlotPanel());
        }

        gamePanel.add(dealerCardArea, BorderLayout.NORTH);
        gamePanel.add(Box.createVerticalStrut(20), BorderLayout.CENTER); 
        gamePanel.add(playerCardArea, BorderLayout.SOUTH);

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


    private void updateCardImage(JLabel cardLabel, String imagePath) {
        ImageIcon newIcon = new ImageIcon(imagePath);
        Image newImg = newIcon.getImage().getScaledInstance(cardLabel.getWidth(), cardLabel.getHeight(), Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImg);
        cardLabel.setIcon(newIcon);

        cardLabel.revalidate();
        cardLabel.repaint();
    }

    private String getImagePathForCard(Card card) {
        return card.getCardImage();
    }

    private void startGame() {
        deck.shuffle(); 
    
        player.initialDeal(deck);
        dealer.initialDeal(deck);

        Hand hand = player.getHands().get(0); 
        for (int cardIndex = 0; cardIndex < hand.getCards().size(); cardIndex++) {
            Card card = hand.getCards().get(cardIndex); 
            String cardImagePath = getImagePathForCard(card); 
            updateCardImage(playerCardSlots[0].getCardLabel(cardIndex), cardImagePath); 
        }
        
        Card deCard1 = dealer.getHand().get(0);

        String cardImagePath = getImagePathForCard(deCard1); 
        updateCardImage(dealerCardSlots[0].getCardLabel(0), cardImagePath); 
        updateCardImage(dealerCardSlots[0].getCardLabel(1), "Classic/Card-Back-05.png"); 
    }
    


    public static void main(String[] args) {
        BJack bJack = new BJack();
        bJack.promptForPlayerNameAndStart();
        // bJack.createAndShowGUI();
        bJack.startGame();
    }
}
