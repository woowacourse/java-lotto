package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int DEFAULT_COUNT = 0;

    private final Map<Rank, Integer> lottoResult = new HashMap<>();

    public LottoResult() {
        for (Rank rank : Rank.values()) {
            lottoResult.put(rank, DEFAULT_COUNT);
        }
    }

    public Map<Rank, Integer> getLottoResult() {
        return lottoResult;
    }

    public void addMatchingCount(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = lotto.getRank(winningLotto);
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        }
    }

    public Profit getProfit() {
        int totalMoney = 0;
        for (Rank rank : Rank.values()) {
            totalMoney += rank.getMoney() * lottoResult.get(rank);
        }
        return new Profit(totalMoney);
    }
}
