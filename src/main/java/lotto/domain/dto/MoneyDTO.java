package lotto.domain.dto;

public class MoneyDTO {
    int money;
    int availableCount;

    int round;

    public int getMoney() {
        return money;
    }

    public void setMoney(final int money) {
        this.money = money;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(final int availableCount) {
        this.availableCount = availableCount;
    }

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

}
