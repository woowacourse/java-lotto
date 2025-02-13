package model;

import constant.WinLottoInfo;
import java.util.EnumMap;

public class Result {
    private final EnumMap<WinLottoInfo, Integer> result = new EnumMap<>(WinLottoInfo.class);

    public Result(PurchasedLottos purchasedLottos, WinLotto winLotto) {
        for (LottoNumbers purchasedLotto : purchasedLottos.getLottos()) {
            WinLottoInfo winResult = WinLottoInfo.result(purchasedLotto, winLotto);
            result.put(winResult, result.getOrDefault(winResult, 0) + 1);
        }
    }

    public Integer getCount(WinLottoInfo winLottoInfo) {
        return result.get(winLottoInfo);
    }
}
