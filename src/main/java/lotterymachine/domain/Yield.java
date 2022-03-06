package lotterymachine.domain;

import static java.lang.Math.floor;

public class Yield {
    private static final double DECIMAL_PLACE_SAVER = 100.0;
    private final double profitRate;

    private Yield(double profitRate) {
        this.profitRate = profitRate;
    }

    public static Yield of(int profit, int investment) {
        return new Yield(createProfitRate(profit, investment));
    }

    private static double createProfitRate(int profit, int investment) {
        return floor(profit / investment * DECIMAL_PLACE_SAVER) / DECIMAL_PLACE_SAVER;
    }

    public double getProfitRate() {
        return this.profitRate;
    }
}
