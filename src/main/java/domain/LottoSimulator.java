package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoSimulator {
    private final WinningLotto winningLotto;

    public LottoSimulator(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public LottoSimulationResult play(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        RankAnalysisBuilder builder = new RankAnalysisBuilder(winningLotto);

        while(Lotto.PRICE.lessThan(money) || Lotto.PRICE.equals(money)) {
            money = money.minus(Lotto.PRICE);

            Lotto lotto = LottoFactory.createRandomLotto();

            builder.add(lotto);
            lottos.add(lotto);
        }

        return LottoSimulationResult.of(builder.toRankAnalysis(), LottoGroup.from(lottos));
    }
}
