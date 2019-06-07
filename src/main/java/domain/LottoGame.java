package domain;

import java.util.List;

public class LottoGame {
    public static Statistics startLottery(List<IssuedLotto> issuedLottos, WinningLotto winningLotto) {
        Statistics statistics = Statistics.of(Rank.values());

        for (IssuedLotto issuedLotto : issuedLottos) {
            Rank matchkingResult = winningLotto.matchUpLottoNumbersOf(issuedLotto);
            statistics.add(matchkingResult);
        }
        return statistics;
    }
}
