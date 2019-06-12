package lotto.controller;

import lotto.model.*;

import java.util.ArrayList;
import java.util.List;

class LottoGame {
    private static final String MESSAGE_LOW_MONEY = "금액이 모자랍니다.";

    private final LottoRule rule;
    private final LottoMaker maker;
    private final Lottos lottos;
    private final WinningLotto winLotto;
    private final int autoPurchaseCount;
    private final WinStat stat;

    private LottoGame(Builder builder) {
        this.rule = builder.rule;
        this.maker = builder.maker;
        this.lottos = builder.lottos;
        this.winLotto = builder.winLotto;

        final int allPurchaseCount = getAllPurchaseCount(builder.purchaseAmount);
        autoPurchaseCount = getAutoPurchaseCount(allPurchaseCount);

        lottos.add(autoLottos(autoPurchaseCount));
        stat = new WinStat(lottos, winLotto, rule);
    }

    public Lottos getLottos() {
        return this.lottos;
    }

    public WinningLotto getWinLotto() {
        return this.winLotto;
    }

    public WinStat getStat() {
        return this.stat;
    }

    public int getAllPurchaseCount() {
        return this.lottos.size();
    }

    public int getAutoPurchaseCount() {
        return this.autoPurchaseCount;
    }

    public int getManualPurchaseCount() {
        return getAllPurchaseCount() - getAutoPurchaseCount();
    }

    private int getAllPurchaseCount(final int purchaseAmount) {
        final int result = purchaseAmount / rule.getPrice();
        if (result < 1) {
            throw new IllegalArgumentException(MESSAGE_LOW_MONEY);
        }
        return result;
    }

    private int getAutoPurchaseCount(final int allPurchaseCount) {
        final int result = allPurchaseCount - lottos.size();
        if (result < 0) {
            throw new IllegalArgumentException(MESSAGE_LOW_MONEY);
        }
        return result;
    }

    private List<Lotto> autoLottos(final int purchaseAmount) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottos.add(maker.getLotto());
        }
        return lottos;
    }

    public static class Builder { // fluent API style
        private final LottoRule rule;
        private final LottoMaker maker;
        private int purchaseAmount;
        private Lottos lottos;
        private WinningLotto winLotto;

        public Builder(final LottoRule rule, final LottoMaker maker) {
            this.rule = rule;
            this.maker = maker;
        }

        public Builder purchaseAmount(final int purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
            return this;
        }

        public Builder purchasedLottos(final Lottos lottos) {
            this.lottos = lottos;
            return this;
        }

        public Builder winLotto(final WinningLotto winLotto) {
            this.winLotto = winLotto;
            return this;
        }

        public LottoGame build() {
            return new LottoGame(this);
        }
    }
}
