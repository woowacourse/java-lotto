package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoGameResult {
    private static final int INIT_NUMBER = 0;
    private static final int PERCENT = 100;

    private final Map<Rank, Integer> lottoStat;

    private LottoGameResult(final List<Lotto> lottos, final WinningLotto winningLotto) {
        this.lottoStat = new HashMap<>();
        init();
        for (final Lotto lotto : lottos) {
            final Rank rank = winningLotto.match(lotto);
            lottoStat.put(rank, lottoStat.get(rank) + 1);
        }
    }

    private void init() {
        for (Rank value : Rank.values()) {
            this.lottoStat.put(value, INIT_NUMBER);
        }
    }

    static LottoGameResult of(final List<Lotto> lottos, final WinningLotto winningLotto) {
        return new LottoGameResult(lottos, winningLotto);
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
