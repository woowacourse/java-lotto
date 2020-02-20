package lotto.domain.result;

import java.util.List;

public class GameResults {
    private final List<GameResult> gameResults;

    public GameResults(List<GameResult> results) {
        this.gameResults = results;
    }

    public int calculateCaseNumberSize(GameResult gameResult) {
        return (int) gameResults.stream()
                .filter(gameResult1 -> gameResult1 == gameResult)
                .count();
    }

    public long calculateBenefit() {
        return gameResults.stream()
                .mapToLong(value -> value.prize)
                .sum();
    }
}
