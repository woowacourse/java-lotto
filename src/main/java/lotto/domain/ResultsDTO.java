package lotto.domain;

import java.util.Map;

public class ResultsDTO {
    private Map<WinningInfo, Integer> matchCount;
    private long earningRate;

    public ResultsDTO(Map<WinningInfo, Integer> matchCount, long earningRate) {
        this.matchCount = matchCount;
        this.earningRate = earningRate;
    }

    public int getMatchCount(WinningInfo winningInfo) {
        return matchCount.getOrDefault(winningInfo, 0);
    }

    public long getEarningRate() {
        return earningRate;
    }
}
