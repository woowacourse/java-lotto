package domain;

import java.util.List;

public class LottoGame {
    public static Statistics startLottery(List<Lotto> lottos, Lotto winningLotto) {
        Statistics statistics = Statistics.of(Rank.values());

        for (Lotto lotto : lottos) {
            statistics.add(lotto.matchUpLottoNumbersWith(winningLotto));
        }
        return statistics;
    }
}
