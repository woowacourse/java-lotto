package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsDTO {
    private List<WinningInfo> results;
    private int earningRate;

    public ResultsDTO(List <WinningInfo> results, int earningRate) {
        this.results = results;
        this.earningRate = earningRate;
    }

    public int getEarningRate() {
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
