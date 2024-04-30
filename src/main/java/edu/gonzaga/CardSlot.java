package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardSlot {
    private JPanel cardPanel;
    private ArrayList<JLabel> cardLabels;
    private JLabel moneyLabel;
    private Integer bet = 0; 



    private static final int CARD_WIDTH = 80; // Adjust width as needed
    private static final int CARD_HEIGHT = 120; // Adjust height as needed

    public CardSlot(Color backgroundColor, ImageIcon defaultCardIcon, boolean isDealer) {
        cardPanel = new JPanel();
        cardPanel.setBackground(backgroundColor);
        cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // Center cards
        cardLabels = new ArrayList<>();

        // Assume each card slot starts with one card by default for the dealer
        if (isDealer) {
            JLabel label = new JLabel(defaultCardIcon);
            cardPanel.add(label);
            cardLabels.add(label);
        } else {
            // For players, include the money label
            JLabel label = new JLabel(defaultCardIcon);
            cardPanel.add(label);
            cardLabels.add(label);
            // Create and add the money label
            moneyLabel = new JLabel("Bet: $" + bet.toString(), JLabel.CENTER);
            moneyLabel.setFont(new Font("Serif", Font.BOLD, 14));
            cardPanel.add(moneyLabel);
        }
    }

    public void clear() {
        for (JLabel label : cardLabels) {
            cardPanel.remove(label); // Remove the label from the panel
        }
        cardLabels.clear(); // Clear the list
    }

    public void addCardImage(ImageIcon cardImage) {
        // Resize the image icon before adding it to the label
        Image image = cardImage.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);

        JLabel label = new JLabel(scaledIcon);
        cardPanel.add(label);
        cardLabels.add(label);
    }

    public void setBet (Integer i){
        bet = i;
    }




    public JPanel getCardSlotPanel() {
        return cardPanel;
    }

    public void setCardImage(ImageIcon cardIcon) {
        // Set the image for the first card label
        if (!cardLabels.isEmpty()) {
            cardLabels.get(0).setIcon(cardIcon);
        }
    }

    public void setMoneyText(String text) {
        moneyLabel.setText(text);
    }

    public JLabel getCardLabel(int i) {
        if (i >= 0 && i < cardLabels.size()) {
            return cardLabels.get(i);
        } else {
            return null;
        }
    }
}
