package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<WinningType, Integer> lottoResult;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottoResult = createLottoResult(lottos, winningLotto);
    }

    private Map<WinningType, Integer> createLottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<WinningType, Integer> result = new HashMap<>();

        for (Lotto lotto : lottos) {
            WinningType winningType = winningLotto.macthLotto(lotto);
            if (winningType != null) {
                result.put(winningType, result.getOrDefault(winningType, 0) + 1);
            }
        }
        return result;
    }

    public int getWinnerTypeValue(WinningType winnerType) {
        return lottoResult.getOrDefault(winnerType, 0);
    }

    public long getRewardAll() {
        long result = 0;
        for (WinningType value : WinningType.values()) {
            result += lottoResult.getOrDefault(value, 0) * value.getReward();
        }
        return result;
    }
}
