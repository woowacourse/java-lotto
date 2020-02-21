package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsDTO {
    private List<WinningInfo> results;
    private long earningRate;

    public ResultsDTO(List <WinningInfo> results, long earningRate) {
        this.results = results;
        this.earningRate = earningRate;
    }

    public long getEarningRate() {
        return earningRate;
    }

    public int getWinningCount(WinningInfo winningInfo) {
        return results
                .stream()
                .filter(result -> result.equals(winningInfo))
                .collect(Collectors.toList())
                .size();
    }
}
