package dataModels;

public class PlayerProfileDataModel {
    private int accountId;
    private int wins;
    private int losses;

    public PlayerProfileDataModel(int accountId, int wins, int losses) {
        this.accountId = accountId;
        this.wins = wins;
        this.losses = losses;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
