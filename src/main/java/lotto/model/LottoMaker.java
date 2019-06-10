package lotto.model;

public abstract class LottoMaker {
    LottoRule rule;

    LottoMaker(LottoRule rule) {
        this.rule = rule;
    }

    public abstract Lotto getLotto();
}
