package lotto.domain;

import lotto.domain.generator.LottoRandomGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.ManualPurchaseCount;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.domain.winningresult.WinningResult;

import java.util.List;
import java.util.Map;

public class LottoMachine {
    private final Lottos lottos;
    private final TotalPurchaseAmount totalPurchaseAmount;
    private final ManualPurchaseCount manualPurchaseCount;

    private LottoMachine(final LottoMachine.Builder builder) {
        this.totalPurchaseAmount = builder.totalPurchaseAmount;
        lottos = new Lottos(new LottoRandomGenerator().generateLottosExceptManualGenerated(
                this.totalPurchaseAmount.getTotalPurchaseCount(), builder.manualLottos));
        manualPurchaseCount = new ManualPurchaseCount(builder.manualLottos.size(), totalPurchaseAmount.getTotalPurchaseCount());
    }

    public static class Builder {
        private int lottoPrice = 1000;
        private TotalPurchaseAmount totalPurchaseAmount;
        private List<Lotto> manualLottos;

        public Builder setLottoPrice(final int lottoPrice) {
            this.lottoPrice = lottoPrice;
            return this;
        }

        public Builder setTotalPurchaseAmount(final int totalPurchaseAmount) {
            this.totalPurchaseAmount = new TotalPurchaseAmount.Builder()
                    .setLottoPrice(lottoPrice)
                    .setTotalAmount(totalPurchaseAmount)
                    .build();
            return this;
        }

        public Builder setManualLottos(final List<Lotto> manualLottos) {
            this.manualLottos = manualLottos;
            return this;
        }

        public LottoMachine build() {
            return new LottoMachine(this);
        }
    }

    public int getCountOfAutoLottoNumbers() {
        return totalPurchaseAmount.getTotalPurchaseCount() - manualPurchaseCount.getValue();
    }

    public int getCountOfManualLottoNumbers() {
        return manualPurchaseCount.getValue();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> winningNumberByMatchKind = lottos.match(winningNumbers);
        return new WinningResult(winningNumberByMatchKind, totalPurchaseAmount);
    }
}
