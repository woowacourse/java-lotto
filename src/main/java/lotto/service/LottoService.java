package lotto.service;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.LottoMatchKind;
import lotto.domain.purchaseamount.PurchaseAmount;
import lotto.domain.winningresult.WinningResult;

import java.util.List;
import java.util.Map;

public class LottoService {
    private final Lottos lottos;
    private final PurchaseAmount purchaseAmount;

    public LottoService(
            final LottoGenerator lottoGenerator, final PurchaseAmount purchaseAmount, final List<Lotto> manualLottos) {
        this.purchaseAmount = purchaseAmount;
        lottos = new Lottos(lottoGenerator.generateLottosExceptManualGenerated(
                this.purchaseAmount.getCountOfLottoNumbers(), manualLottos));
    }

    public int getCountOfLottoNumbers() {
        return purchaseAmount.getCountOfLottoNumbers();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final WinningNumbers winningNumbers) {
        final Map<LottoMatchKind, Integer> winningNumberByMatchKind = lottos.match(winningNumbers);
        return new WinningResult(winningNumberByMatchKind, purchaseAmount);
    }
}
