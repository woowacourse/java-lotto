package lotto.controller;

import lotto.domain.*;

public class GameController {
    public static ResultsDTO run(Lottos lottos, WinningLotto winningLotto) {
        MatchResults matchResults = ResultCalculator.computeMatchResults(lottos, winningLotto);
        long totalEarning = ResultCalculator.computeTotalEarning(matchResults);
        long earningRate = ResultCalculator.computeEarningRate(totalEarning, lottos.getLottosSize());
        return new ResultsDTO(matchResults, earningRate);
    }
}
