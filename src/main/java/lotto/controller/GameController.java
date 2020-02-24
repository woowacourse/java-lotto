package lotto.controller;

import lotto.domain.*;

import java.util.List;

public class GameController {
    public static ResultsDTO run(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = ResultCalculator.computeResults(lottos, winningLotto);
        long totalEarning = ResultCalculator.computeTotalEarning(results);
        long earningRate = ResultCalculator.computeEarningRate(totalEarning, results.size());
        MatchResults matchCount = ResultCalculator.computeTotalMatchCount(results);
        return new ResultsDTO(matchCount, earningRate);
    }
}
