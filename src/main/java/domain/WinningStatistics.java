package domain;

import java.util.Map;

public class WinningStatistics {
    private final Map<LottoPrize, Integer> prizeCounter;

    public WinningStatistics(Map<LottoPrize, Integer> prizeCounter) {
        this.prizeCounter = prizeCounter;
    }

    public Map<LottoPrize, Integer> getPrizeCounter() {
        return prizeCounter;
    }
}
