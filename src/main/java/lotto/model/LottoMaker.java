package lotto.model;

import java.util.List;

public abstract class LottoMaker {
    LottoRule rule;

    LottoMaker(LottoRule rule) {
        this.rule = rule;
    }

    public abstract Lotto getLotto();

    public abstract List<Lotto> getAutoLottos(final int purchaseAmount);
}
