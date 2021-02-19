package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<Result> checkLottoResults(WinningNumber winningNumber, BonusNumber bonusNumber) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            results.add(Result.decisionLottoRank(
                    lotto.matchingCount(winningNumber),
                    lotto.isBonusMatch(bonusNumber.getBonus())
            ));
        }
        return results;
    }
}
