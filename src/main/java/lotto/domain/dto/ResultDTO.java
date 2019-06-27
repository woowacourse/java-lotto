package lotto.domain.dto;

public class ResultDTO {

    private int round;
    private int firstPrize;
    private int secondPrize;
    private int thirdPrize;
    private int forthPrize;
    private int fifthPrize;
    private double profitRate;
    private int money;

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public void setFirstPrize(final int firstPrize) {
        this.firstPrize = firstPrize;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public void setSecondPrize(final int secondPrize) {
        this.secondPrize = secondPrize;
    }

    public int getThirdPrize() {
        return thirdPrize;
    }

    public void setThirdPrize(final int thirdPrize) {
        this.thirdPrize = thirdPrize;
    }

    public int getForthPrize() {
        return forthPrize;
    }

    public void setForthPrize(final int forthPrize) {
        this.forthPrize = forthPrize;
    }

    public int getFifthPrize() {
        return fifthPrize;
    }

    public void setFifthPrize(final int fifthPrize) {
        this.fifthPrize = fifthPrize;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(final double profitRate) {
        this.profitRate = profitRate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(final int money) {
        this.money = money;
    }
}
