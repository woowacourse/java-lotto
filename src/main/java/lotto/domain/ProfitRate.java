package lotto.domain;

public class ProfitRate {

    private final double profitRate;

    public ProfitRate(long totalPrize, Money money) {
        this.profitRate = (double) totalPrize / (double) money.getMoney();
    }

    public String toStringProfitRate() {
        return String.valueOf(Math.floor(profitRate * 100) / 100.0);
    }
}
