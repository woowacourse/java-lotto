package model;

import constant.WinLottoInfo;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class LottoStatistics {
    private final EnumMap<WinLottoInfo, Integer> lottoStatistics = new EnumMap<>(WinLottoInfo.class);

    public LottoStatistics(List<LottoNumbers> lottoNumbers, WinLotto winLotto) {
        for (LottoNumbers purchasedLotto : lottoNumbers) {
            WinLottoInfo winResult = WinLottoInfo.result(purchasedLotto, winLotto);
            lottoStatistics.put(winResult, lottoStatistics.getOrDefault(winResult, 0) + 1);
        }
    }

    private Integer calculateTotalPrize() {
        int sum = 0;
        for (Entry<WinLottoInfo, Integer> statisticsEntry : lottoStatistics.entrySet()) {
            WinLottoInfo winLottoInfo = statisticsEntry.getKey();
            Integer count = statisticsEntry.getValue();
            sum += winLottoInfo.getPrice() * count;
        }
        return sum;
    }

    public Integer getCount(WinLottoInfo winLottoInfo) {
        return lottoStatistics.get(winLottoInfo);
    }

    public Double totalReturn(Integer purchaseAmount) {
        return (double) calculateTotalPrize() / purchaseAmount;
    }
}
