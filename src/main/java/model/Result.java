package model;

import constant.WinLottoInfo;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Result {
    private final EnumMap<WinLottoInfo, Integer> result = new EnumMap<>(WinLottoInfo.class);

    public Result(List<LottoNumbers> lottoNumbers, WinLotto winLotto) {
        for (LottoNumbers purchasedLotto : lottoNumbers) {
            WinLottoInfo winResult = WinLottoInfo.result(purchasedLotto, winLotto);
            result.put(winResult, result.getOrDefault(winResult, 0) + 1);
        }
    }

    private int calculateTotalPrize() {
        int sum = 0;
        for (Entry<WinLottoInfo, Integer> winLottoInfoIntegerEntry : result.entrySet()) {
            WinLottoInfo winLottoInfo = winLottoInfoIntegerEntry.getKey();
            int count = winLottoInfoIntegerEntry.getValue();
            int prizePerType = winLottoInfo.getPrice();
            int totalPrizeForThisType = prizePerType * count;
            sum += totalPrizeForThisType;
        }
        return sum;
    }

    public int getCount(WinLottoInfo winLottoInfo) {
        return result.get(winLottoInfo);
    }

    public double totalReturn(int purchaseAmount) {
        return (double) calculateTotalPrize() / purchaseAmount;
    }
}
