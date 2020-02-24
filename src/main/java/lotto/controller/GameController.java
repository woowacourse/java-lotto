package lotto.controller;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

public class GameController {
    public static ResultsDTO run(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = ResultCalculator.computeResults(lottos, winningLotto);
        long totalEarning = ResultCalculator.computeTotalEarning(results);
        long earningRate = ResultCalculator.computeEarningRate(totalEarning, results.size());
        Map<WinningInfo, Integer> matchCount = ResultCalculator.computeTotalMatchCount(results);
        return new ResultsDTO(matchCount, earningRate);
    }
}
