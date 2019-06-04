package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGameResult {
    private static final int INIT_NUMBER = 0;
    private static final int PERCENT = 100;

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> lottoStat;

    private LottoGameResult(final List<Lotto> lottos) {
        this.lottos = lottos;
        this.lottoStat = new HashMap<>();
        for (Rank value : Rank.values()) {
            this.lottoStat.put(value, INIT_NUMBER);
        }
    }

    static LottoGameResult of(final List<Lotto> lottos) {
        return new LottoGameResult(lottos);
    }

    public void match(final WinningLotto winningLotto) {
        for (final Lotto lotto : lottos) {
            final Rank rank = winningLotto.match(lotto);
            lottoStat.put(rank, lottoStat.get(rank) + 1);
        }
    }

    public int getRankCount(final Rank rank) {
        return lottoStat.get(rank);
    }

    public double profit(final int lottoCost) {
        double numOfLotto = 0;
        double totalMoney = 0;

        for (Rank rank : lottoStat.keySet()) {
            final int tempNum = lottoStat.get(rank);
            numOfLotto += tempNum;
            totalMoney += rank.prize(tempNum);
        }
        return totalMoney / (numOfLotto * lottoCost) * PERCENT;
    }
}
