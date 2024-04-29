package edu.gonzaga;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerInfo {
    private static final String FILENAME = "players.txt";
    private Map<String, Integer> playerBalances;

    public PlayerInfo() {
        playerBalances = new HashMap<>();
        loadPlayerBalances();
    }

    public boolean playerExists(String name) {
        return playerBalances.containsKey(name);
    }


    private void loadPlayerBalances() {
        try {
            // Read all lines from the file
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

    public int getBalance(String name) {
        return playerBalances.computeIfAbsent(name, k -> {
            savePlayerBalances(); // Save when a new player is added
            return 1000;
        });
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
