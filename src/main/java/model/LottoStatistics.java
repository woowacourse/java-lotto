package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class LottoStatistics {
    private final EnumMap<LottoWinRank, Integer> lottoStatistics = new EnumMap<>(LottoWinRank.class);

    public LottoStatistics(List<LottoNumbers> lottoNumbers, WinLotto winLotto) {
        for (LottoNumbers purchasedLotto : lottoNumbers) {
            LottoWinRank winResult = LottoWinRank.calculateLottoWinRank(purchasedLotto, winLotto);
            lottoStatistics.put(winResult, lottoStatistics.getOrDefault(winResult, 0) + 1);
        }
    }

    private Integer calculateTotalPrize() {
        int sum = 0;
        for (Entry<LottoWinRank, Integer> statisticsEntry : lottoStatistics.entrySet()) {
            LottoWinRank lottoWinRank = statisticsEntry.getKey();
            Integer count = statisticsEntry.getValue();
            sum += lottoWinRank.getPrice() * count;
        }
        return sum;
    }

    public Integer getCount(LottoWinRank lottoWinRank) {
        return lottoStatistics.get(lottoWinRank);
    }

    public Double totalReturn(Integer purchaseAmount) {
        return (double) calculateTotalPrize() / purchaseAmount;
    }

    public Set<Entry<LottoWinRank, Integer>> getStatisticsEntries() {
        return lottoStatistics.entrySet();
    }
}
