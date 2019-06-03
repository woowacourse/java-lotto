package lotto.domain;

public class Prize {
    private long prize;

    public Prize() {
        this.prize = 0;
    }

    public void addPrize(long winningMoney) {
        this.prize += winningMoney;
    }

    public double divideByMoney(Money money) {
        return money.prizeDivideMoney(prize);
    }

}
