package domain;

public class ProfitPercent {
    private int profitPercent;

    public ProfitPercent(Money money, int totalPrizeMoney) {
        Double profitPercent = Double.valueOf(totalPrizeMoney) / Double.valueOf(money.getMoney()) * 100;
        this.profitPercent = profitPercent.intValue();
    }

    public int getProfitPercent() {
        return this.profitPercent;
    }
}
