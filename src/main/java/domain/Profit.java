package domain;

import java.util.Map;

public class Profit {

    private final double result;

    private Profit(double result) {
        this.result = result;
    }

    public static Profit of(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double result = calculate(calculateResult, purchaseAmount);
        return new Profit(result);
    }

    public static double calculate(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double totalPrize = 0;
        for (Rank rank : calculateResult.keySet()) {
            totalPrize += rank.getPrize() * calculateResult.get(rank);
        }
        return Math.floor((totalPrize / purchaseAmount) * 100) / 100;
    }

    public double getResult() {
        return result;
    }
}
