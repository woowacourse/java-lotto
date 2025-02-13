package model;

import constant.WinLottoInfo;
import java.util.EnumMap;
import java.util.Map.Entry;

public class Result {
    private final EnumMap<WinLottoInfo, Integer> result = new EnumMap<>(WinLottoInfo.class);
    private final Integer totalPrize;

    public Result(Purchase purchase, WinLotto winLotto) {
        for (LottoNumbers purchasedLotto : purchase.getLottos()) {
            WinLottoInfo winResult = WinLottoInfo.result(purchasedLotto, winLotto);
            result.put(winResult, result.getOrDefault(winResult, 0) + 1);
        }
        totalPrize = calculateTotalPrize();
    }

    private Integer calculateTotalPrize() {
        Integer sum = 0;
        for (Entry<WinLottoInfo, Integer> winLottoInfoIntegerEntry : result.entrySet()) {
            WinLottoInfo winLottoInfo = winLottoInfoIntegerEntry.getKey();
            Integer count = winLottoInfoIntegerEntry.getValue();
            sum += winLottoInfo.getPrice() * count;
        }
        return sum;
    }

    public Integer getCount(WinLottoInfo winLottoInfo) {
        return result.get(winLottoInfo);
    }
}
