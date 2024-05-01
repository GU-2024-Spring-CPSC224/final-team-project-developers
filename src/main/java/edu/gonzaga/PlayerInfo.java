package edu.gonzaga;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfo {
    private static final String FILENAME = "players.txt";
    private Map<String, Integer> playerBalances;
    public Integer balance = 0; 

    public PlayerInfo() {
        playerBalances = new HashMap<>();
        playerBalances.put("Gavin", 2000);
        // loadPlayerBalances();
    }

    public boolean playerExists(String name) {
        if (playerBalances.containsKey(name)){
            this.balance = playerBalances.get(name);
            return true;
        }
        else return false;
    }


    public void printPlayerBalances() {
        for (Map.Entry<String, Integer> entry : playerBalances.entrySet()) {
            System.out.println("Player: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    private void loadPlayerBalances() {
        try {
            Path path = Paths.get(FILENAME);
            if (Files.exists(path)) {
                Files.readAllLines(path).forEach(line -> {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        playerBalances.put(parts[0], Integer.parseInt(parts[1]));
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(String name, int balance) {
        playerBalances.put(name, balance);
        savePlayerBalances();
    }

    private void savePlayerBalances() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILENAME))) {
            for (Map.Entry<String, Integer> entry : playerBalances.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
