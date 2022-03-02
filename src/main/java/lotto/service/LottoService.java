package lotto.service;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.domain.winningresult.WinningResult;

import java.util.List;
import java.util.Map;

public class LottoService {
    private final Lottos lottos;
    private final TotalPurchaseAmount totalPurchaseAmount;

    public LottoService(
            final LottoGenerator lottoGenerator, final TotalPurchaseAmount totalPurchaseAmount, final List<Lotto> manualLottos) {
        this.totalPurchaseAmount = totalPurchaseAmount;
        lottos = new Lottos(lottoGenerator.generateLottosExceptManualGenerated(
                this.totalPurchaseAmount.getCountOfLottoNumbers(), manualLottos));
    }

    public int getCountOfLottoNumbers() {
        return totalPurchaseAmount.getCountOfLottoNumbers();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> winningNumberByMatchKind = lottos.match(winningNumbers);
        return new WinningResult(winningNumberByMatchKind, totalPurchaseAmount);
    }
}
