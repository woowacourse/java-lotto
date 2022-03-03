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
        return Map.copyOf(lottoResult);
    }

    public void addMatchingCount(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winningLotto.getLottoRank(lotto);
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        }
    }

    public double calculateProfitRate(Money payment) {
        return (double)getProfit().getMoney() / payment.getMoney();
    }

    private Money getProfit() {
        return Rank.getTotalWinningPrize(lottoResult);
    }
}
