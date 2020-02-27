package lotto.domain.result;

import java.util.List;

public class GameResults {
    static final int MULTIPLE_PERCENTAGE = 100;

    private final List<GameResult> gameResults;

    public GameResults(List<GameResult> results) {
        this.gameResults = results;
    }

    public int calculateCaseNumberSize(GameResult gameResult) {
        return (int) gameResults.stream()
                .filter(gameResult1 -> gameResult1 == gameResult)
                .count();
    }

    private double calculateBenefit() {
        return gameResults.stream()
                .mapToDouble(GameResult::getPrize)
                .sum();
    }

    public double calculateYield(Money money) {
        double purchaseMoney = money.getMoney();
        double benefit = calculateBenefit();
        return (benefit / purchaseMoney) * MULTIPLE_PERCENTAGE;
    }
}
