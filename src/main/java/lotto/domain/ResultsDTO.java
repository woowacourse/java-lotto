package lotto.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ResultsDTO {
    private ArrayList<WinningInfo> results;
    private int totalEarning;
    private int earningRate;

    public ResultsDTO(ArrayList<WinningInfo> results, int totalEarning, int earningRate) {
        this.results = results;
        this.totalEarning = totalEarning;
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
