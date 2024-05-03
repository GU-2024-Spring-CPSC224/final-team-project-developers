package edu.gonzaga;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;


public class TitleGUI extends JDialog {
    private JButton singleplayerButton;
    private JButton multiplayerButton;
    private boolean clicked = false;
    private String answer = "";
    public PlayerBalanceInfo playerBalanceInfo;
    private BJack game = new BJack();

    PlayerInfo playerInfo = new PlayerInfo();

    public TitleGUI() {
        setTitle("Blackjack");
        setSize(1000, 1050);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon("Classic/image.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        singleplayerButton = new JButton("Singleplayer");
        singleplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clicked = true; 
                dispose(); // Close the dialog
                game.playerInfo.balance = 10000;
                game.balanceLabel = new JLabel("Balance: $" + 10000);
                game.player.setName("");
                game.player.setBalance(10000);
                game.createAndShowGUI();
                game.promptForBetAmount();
                game.startGame();
            }
        });
        buttonPanel.add(singleplayerButton);

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle multiplayer button click
                promptMultiplayerNames();
                clicked = true; 
                dispose(); // Close the dialog
            }
        });
        buttonPanel.add(multiplayerButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public boolean wasClicked() {
        return clicked;
    }

    public PlayerBalanceInfo getAnswer() {
       
        String name = answer; 
        Integer balance = playerInfo.playerBalances.get(name);
        System.out.println(balance);
        playerBalanceInfo = new PlayerBalanceInfo(name, balance);

        return playerBalanceInfo;
    }

    private void removeButtons() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }

    private void promptMultiplayerNames() {
        String name = JOptionPane.showInputDialog(null, "Enter Player 1's Name:");
        if (name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z0-9]*$")) {
            if (playerInfo.playerExists(name)) {
                int balance = playerInfo.playerBalances.get(name);
                game.playerInfo.balance = balance;
                game.balanceLabel = new JLabel("Balance: $" + balance);
                game.player.setName(name);
                game.player.setBalance(balance);
                dispose(); 
                game.createAndShowGUI();
                game.promptForBetAmount();
                game.startGame();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user name", "Error", JOptionPane.ERROR_MESSAGE);
                promptMultiplayerNames();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Illegal name entered. Please use only letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            promptMultiplayerNames();
        }



        // Both players' names are valid, proceed with starting the game
        JOptionPane.showMessageDialog(null, "Starting multiplayer game for Player : " + name);
        // Implement your multiplayer game logic here
    }
    public static void main(String[] args){
        TitleGUI game = new TitleGUI();
    }
}

