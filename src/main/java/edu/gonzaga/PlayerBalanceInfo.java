package edu.gonzaga;

public class PlayerBalanceInfo {
    private String name;
    private Integer balance;

    public PlayerBalanceInfo(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }
}
