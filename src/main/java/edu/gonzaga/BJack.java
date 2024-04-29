package edu.gonzaga;

import javax.swing.*;
import java.awt.*;

public class BJack {

    private static JPanel[] dealerCardSlots = new JPanel[1];
    private static JPanel[] playerCardSlots = new JPanel[4];
    private String basicCard ; 


    public BJack(){
        basicCard = "Classic/basic.png"; 
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Blackjack Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600); // Landscape orientation

        Color tableColor = new Color(135, 152, 122);

        // Panel for buttons
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

        JLabel balanceLabel = new JLabel("Balance: $10,000", JLabel.CENTER);
        customizeLabel(balanceLabel);

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(balanceLabel);
        buttonPanel.add(doubleButton);
        buttonPanel.add(splitButton);
        

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(tableColor);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.PAGE_AXIS));

  

        JPanel dealerCardArea = createCardArea(tableColor, 1, dealerCardSlots, this.basicCard);
        JPanel playerCardArea = createCardArea(tableColor, 4, playerCardSlots, this.basicCard);
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
        // Optionally set the background color of the button
        // button.setBackground(Color.WHITE);
    }

    private static void customizeLabel(JLabel label) {
        label.setPreferredSize(new Dimension(150, 100));
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setOpaque(true);
    }

    private static JPanel createCardArea(Color backgroundColor, int numSlots, JPanel[] cardSlots, String cardImagePath) {
        JPanel cardArea = new JPanel();
        cardArea.setBackground(backgroundColor);
        cardArea.setLayout(new GridLayout(1, numSlots, 10, 0)); 
    
        ImageIcon cardIcon = new ImageIcon(cardImagePath);
        Image image = cardIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
        cardIcon = new ImageIcon(image);
    
        for (int i = 0; i < numSlots; i++) {
            JPanel cardSlot = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            cardSlot.setBackground(backgroundColor);
            cardSlot.setPreferredSize(new Dimension(180, 120));
            
            JLabel card1 = new JLabel(cardIcon);
            JLabel card2 = new JLabel(cardIcon);
            cardSlot.add(card1);
            cardSlot.add(card2);
    
            cardArea.add(cardSlot);
            cardSlots[i] = cardSlot;
        }
    
        return cardArea;
    }
    

    private static void updateCardImage(JLabel cardLabel, String imagePath) {
        ImageIcon newIcon = new ImageIcon(imagePath);
        Image newImg = newIcon.getImage().getScaledInstance(cardLabel.getWidth(), cardLabel.getHeight(), Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImg);
        cardLabel.setIcon(newIcon);
    
        cardLabel.revalidate();
        cardLabel.repaint();
    }


    public static void main(String[] args) {
        BJack bJack = new BJack();
        bJack.createAndShowGUI();
    }
}
