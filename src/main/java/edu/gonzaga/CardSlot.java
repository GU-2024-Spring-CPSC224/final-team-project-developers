package edu.gonzaga;

import javax.swing.*;
import java.awt.*;

public class CardSlot {
    private JPanel cardSlotPanel;
    private JLabel card1;
    private JLabel card2;
    private JLabel moneyLabel;

    public CardSlot(Color backgroundColor, ImageIcon cardIcon) {
        cardSlotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cardSlotPanel.setBackground(backgroundColor);
        cardSlotPanel.setPreferredSize(new Dimension(180, 120));

        card1 = new JLabel(cardIcon);
        card2 = new JLabel(cardIcon);
        cardSlotPanel.add(card1);
        cardSlotPanel.add(card2);

        // Create and add the money label
        moneyLabel = new JLabel("Money: $0", JLabel.CENTER);
        moneyLabel.setFont(new Font("Serif", Font.BOLD, 14));
        cardSlotPanel.add(moneyLabel);
    }

    public JPanel getCardSlotPanel() {
        return cardSlotPanel;
    }

    public void setCardImage(ImageIcon cardIcon) {
        card1.setIcon(cardIcon);
    }

    public void setMoneyText(String text) {
        moneyLabel.setText(text);
    }

    public  JLabel getCardLabel(Integer i){
        if (i == 0) return card1;
        else return card2;
    }
}
