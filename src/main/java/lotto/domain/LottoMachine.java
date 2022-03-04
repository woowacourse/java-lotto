package lotto.domain;

import lotto.domain.generator.LottoRandomGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.domain.winningresult.WinningResult;

import java.util.List;
import java.util.Map;

public class LottoMachine {
    private final Lottos lottos;
    private final TotalPurchaseAmount totalPurchaseAmount;

    public LottoMachine(final TotalPurchaseAmount totalPurchaseAmount,
                        final List<Lotto> manualLottos) {
        this.totalPurchaseAmount = totalPurchaseAmount;
        lottos = new Lottos(new LottoRandomGenerator().generateLottosExceptManualGenerated(
                this.totalPurchaseAmount.getCountOfTotalLottoNumbers(), manualLottos));
    }

    public int getCountOfAutoLottoNumbers() {
        return totalPurchaseAmount.getCountOfAutoLottoNumbers();
    }

    public int getCountOfManualLottoNumbers() {
        return totalPurchaseAmount.getCountOfManualLottoNumbers();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> winningNumberByMatchKind = lottos.match(winningNumbers);
        return new WinningResult(winningNumberByMatchKind, totalPurchaseAmount);
    }
}
