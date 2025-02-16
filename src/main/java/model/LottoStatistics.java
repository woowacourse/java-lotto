package model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class LottoStatistics {
    private final EnumMap<LottoWinRank, Integer> lottoStatistics = new EnumMap<>(LottoWinRank.class);

    public LottoStatistics(List<LottoNumbers> lottoNumbers, WinLotto winLotto) {
        Arrays.stream(LottoWinRank.values()).forEach(lottoWinRank -> lottoStatistics.put(lottoWinRank, 0));
        for (LottoNumbers purchasedLotto : lottoNumbers) {
            LottoWinRank winResult = LottoWinRank.calculateLottoWinRank(purchasedLotto, winLotto);
            lottoStatistics.put(winResult, lottoStatistics.get(winResult) + 1);
        }
        lottoStatistics.remove(LottoWinRank.NONE);
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

    private Integer calculateTotalPrize() {
        int sum = 0;
        for (Entry<LottoWinRank, Integer> statisticsEntry : lottoStatistics.entrySet()) {
            LottoWinRank lottoWinRank = statisticsEntry.getKey();
            Integer count = statisticsEntry.getValue();
            sum += lottoWinRank.getPrice() * count;
        }
        return sum;
    }
}
