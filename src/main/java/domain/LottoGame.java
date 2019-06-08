package domain;

public class LottoGame {
    public static Statistics startLottery(IssuedLottos issuedLottos, WinningLotto winningLotto) {
        Statistics statistics = Statistics.of(Rank.values());

        for (IssuedLotto issuedLotto : issuedLottos.getLottos()) {
            Rank matchingResult = winningLotto.matchUpLottoNumbersWith(issuedLotto);
            statistics.add(matchingResult);
        }
        return statistics;
    }
}
