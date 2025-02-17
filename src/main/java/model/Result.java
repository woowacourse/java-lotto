package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class Result {
    private final EnumMap<WinLottoInfo, Integer> result = new EnumMap<>(WinLottoInfo.class);

    public Result(List<WinLottoInfo> winResults) { // ✅ 당첨 정보를 외부에서 주입
        for (WinLottoInfo winResult : winResults) {
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
        return result.getOrDefault(winLottoInfo, 0);
    }

    public double totalReturn(int purchaseAmount) {
        return (double) calculateTotalPrize() / purchaseAmount;
    }
}
