package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeResult {

    private final Map<Rank, Integer> prizeResult;

    public PrizeResult(List<Lotto> lottos, WinningNumbers winningNumber) {
        this.prizeResult = new HashMap<>();
        initFinalResult();
        calculatePrizeResult(lottos, winningNumber);
    }

    private void initFinalResult() {
        Rank.getWinnerRanks()
                .forEach(winnerRank -> prizeResult.put(winnerRank, 0));
    }

    private void calculatePrizeResult(List<Lotto> lottos, WinningNumbers winningNumber) {
        for (Lotto lotto : lottos) {
            final Rank rank = Rank.calculateRank(lotto, winningNumber);
            updatePrizeResult(rank);
        }
    }

    private void updatePrizeResult(Rank winnerRank) {
        prizeResult.put(winnerRank, prizeResult.get(winnerRank) + 1);
    }

    public float earningRate(int inputMoney) {
        final float earningRate = (float) totalPrize() / inputMoney;
        return (float) (Math.floor(earningRate * 100) / 100.0);
    }

    private int totalPrize() {
        int totalPrize = 0;
        for (Rank winnerRank : prizeResult.keySet()) {
            totalPrize += winnerRank.getPrize() * prizeResult.get(winnerRank);
        }
        return totalPrize;
    }

    public Map<Rank, Integer> getPrizeResult() {
        return Collections.unmodifiableMap(prizeResult);
    }
}
