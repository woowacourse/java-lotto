package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultMatcher {
    private static final int INIT_NUMBER = 0;
    private static final int INIT_SUM = 0;
    private static final int INIT_COUNT = 0;
    private static final int PLUS_COUNT = 1;
    private static final int PERCENT = 100;

    private final List<Lotto> lottos;
    private final Map<Rank, Integer> lottoStat;

    public static GameResultMatcher of(final List<Lotto> lottos) {
        return new GameResultMatcher(lottos);
    }

    private GameResultMatcher(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
        this.lottoStat = new HashMap<>();
        for (Rank value : Rank.values()) {
            this.lottoStat.put(value, INIT_NUMBER);
        }
    }

    public void match(final WinningLotto winningLotto) {
        for (final Lotto lotto : lottos) {
            final Rank rank = winningLotto.match(lotto);
            lottoStat.put(rank, lottoStat.get(rank) + PLUS_COUNT);
        }
    }

    public int getRankCount(final Rank rank) {
        return lottoStat.get(rank);
    }

    public double profit(final int lottoCost) {
        double numOfLotto = INIT_COUNT;
        double totalMoney = INIT_SUM;

        for (Rank rank : lottoStat.keySet()) {
            final int tempNum = lottoStat.get(rank);
            numOfLotto += tempNum;
            totalMoney += rank.prize(tempNum);
        }
        return totalMoney / (numOfLotto * lottoCost) * PERCENT;
    }
}
