package lotto.dto;

public class LottoGameDto {
    public int round;
    public int money;
    public int countOfManual;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(final int money) {
        this.money = money;
    }

    public int getCountOfManual() {
        return countOfManual;
    }

    public void setCountOfManual(final int countOfManual) {
        this.countOfManual = countOfManual;
    }
}
