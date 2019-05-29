package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<WinnerType, Integer> lottoResult;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottoResult = createLottoResult(lottos, winningLotto);
    }

    private Map<WinnerType, Integer> createLottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<WinnerType, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            int matchNum = winningLotto.countMatchNum(lotto);
            result.put(WinnerType.valueOf(matchNum), result.getOrDefault(WinnerType.valueOf(matchNum), 0) + 1);
        }

        return result;
    }

    public int getWinnerTypeValue(WinnerType winnerType) {
        return lottoResult.getOrDefault(winnerType, 0);
    }

}
