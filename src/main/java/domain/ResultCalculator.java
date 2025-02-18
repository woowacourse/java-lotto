package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultCalculator {

    private final Map<Rank, Integer> calculateResult;

    private ResultCalculator() {
        this.calculateResult = new LinkedHashMap<>();
        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }
    }

    public static ResultCalculator create() {
        return new ResultCalculator();
    }

    public void calculate(WinningNumber winningNumber, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            int matchCount = winningNumber.calculateMatchNumber(lotto);
            boolean hasBonusNumber = winningNumber.hasBonusNumber(lotto);
            Rank rank = Rank.findRank(matchCount, hasBonusNumber);
            calculateResult.put(rank, calculateResult.get(rank) + 1);
        }
    }

    public Map<Rank, Integer> getCalculateResult() {
        return Collections.unmodifiableMap(calculateResult);
    }
}
