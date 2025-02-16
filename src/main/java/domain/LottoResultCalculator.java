package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResultCalculator {
    public Map<Rank, Integer> calculateMatchingRank(WinningInfo winningInfo, List<Lotto> lottoBundle) {
        Map<Rank, Integer> calculateResult = new LinkedHashMap<>();

        for (Rank value : Rank.values()) {
            calculateResult.put(value, 0);
        }

        for (Lotto lotto : lottoBundle) {
            Rank foundRank = findRank(winningInfo, lotto);
            calculateResult.put(foundRank, calculateResult.get(foundRank) + 1);
        }

        return calculateResult;
    }

    private Rank findRank(WinningInfo winningInfo, Lotto lotto) {
        int matchCount = lotto.calculateMatchCount(winningInfo.getWinningLotto());
        boolean matchBonus = lotto.hasBonusNumber( winningInfo.getBonusNumber());
        return Rank.findRank(matchCount, matchBonus);
    }

    public double calculateProfit(Map<Rank, Integer> calculateResult, int purchaseAmount) {
        double totalPrize = 0;

        for (Rank rank : calculateResult.keySet()) {
            totalPrize += rank.getPrize() * calculateResult.get(rank);
        }

        return Math.floor((totalPrize / purchaseAmount) * 100) / 100;
    }
}
