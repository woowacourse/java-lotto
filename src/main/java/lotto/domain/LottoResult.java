package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_WINNING_TYPE_VALUE = 0;

    private final Map<WinningType, Integer> lottoResult;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        this.lottoResult = createLottoResult(lottos, winningLotto);
    }

    private Map<WinningType, Integer> createLottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<WinningType, Integer> result = initLottoResult();

        for (Lotto lotto : lottos) {
            WinningType winningType = winningLotto.matchLotto(lotto);
            result.put(winningType, result.get(winningType) + 1);
        }
        return result;
    }

    private Map<WinningType, Integer> initLottoResult() {
        Map<WinningType, Integer> result = new HashMap<>();
        for (WinningType value : WinningType.values()) {
            result.put(value, DEFAULT_WINNING_TYPE_VALUE);
        }
        return result;
    }

    public int getWinnerTypeValue(WinningType winnerType) {
        return lottoResult.get(winnerType);
    }

    public long getRewardAll() {
        long result = 0;
        for (WinningType value : WinningType.values()) {
            result += lottoResult.get(value) * value.getReward();
        }
        return result;
    }

    public Map<WinningType, Integer> getLottoResult() {
        return lottoResult;
    }

    public double calculateLottoYield(Money money) {
        return ((double) getRewardAll() / money.getMoney()) * 100;
    }
}
