package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleGUI extends JFrame {
    private JButton singleplayerButton;
    private JButton multiplayerButton;
    String answer ="";

    public String TitleGUI() {

        setTitle("Blackjack");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Load the image
        ImageIcon imageIcon = new ImageIcon("Classic/blackjack-logo-green-ribbon-on-600w-1639300576.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        singleplayerButton = new JButton("Singleplayer");
        singleplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle singleplayer button click
                startGame(1);
            }
        });
        buttonPanel.add(singleplayerButton);

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle multiplayer button click
                removeButtons();
            }
        });
        buttonPanel.add(multiplayerButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
        return answer;
    }

    private void startGame(int mode) {
        if (mode == 1) {
            JOptionPane.showMessageDialog(null, "Starting singleplayer game...");
            // Implement your singleplayer game logic here
        } else if (mode == 2) {
            JOptionPane.showMessageDialog(null, "Starting multiplayer game...");
            // Implement your multiplayer game logic here
        }
    }

    private void removeButtons() {
        getContentPane().removeAll();
        revalidate();
        repaint();
    }

    private void promptMultiplayerNames() {
        // Instantiate PlayerInfo object
        PlayerInfo playerInfo = new PlayerInfo();

        // Prompt for Player 1's name
        String player1Name = JOptionPane.showInputDialog(null, "Enter Player 1's Name:");
        if (player1Name != null && !player1Name.trim().isEmpty() && player1Name.matches("^[a-zA-Z0-9]*$")) {
            // Check if player1Name already exists
            if (playerInfo.playerExists(player1Name)) {
                answer = player1Name;
            } else {
                JOptionPane.showMessageDialog(null, "Wrong user name", "Error", JOptionPane.ERROR_MESSAGE);
                promptMultiplayerNames();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Illegal name entered. Please use only letters and numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            promptMultiplayerNames();
        }



        // Both players' names are valid, proceed with starting the game
        JOptionPane.showMessageDialog(null, "Starting multiplayer game for Player 1: " + player1Name);
        // Implement your multiplayer game logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TitleGUI();
            }
        });
    }
}

