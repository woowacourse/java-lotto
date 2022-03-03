package lotto.domain;

import java.util.Collections;
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
        return Collections.unmodifiableMap(lottoResult);
    }

    public void addMatchingCount(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = lotto.getRank(winningLotto);
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        }
    }

    private Money getProfit() {
        Money profit = new Money(0);

        for (Rank rank : Rank.values()) {
            Money prizeMoney = rank.getPrizeMoney();
            prizeMoney.multiply(lottoResult.get(rank));
            profit.add(prizeMoney.getMoney());
        }

        return profit;
    }

    public double calculateProfitRate(Money purchaseAmount) {
        return (double)getProfit().getMoney() / purchaseAmount.getMoney();
    }
}
