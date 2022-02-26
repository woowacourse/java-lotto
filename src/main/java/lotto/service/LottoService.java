package lotto.service;

import lotto.domain.WinningResult;
import lotto.domain.generator.LottoGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.purchaseamount.PurchaseAmount;

import java.util.List;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final PurchaseAmount purchaseAmount;

    public LottoService(final LottoGenerator lottoGenerator, final String purchaseAmount) {
        this.purchaseAmount = PurchaseAmount.fromPurchaseAmountAndLottoPrice(purchaseAmount, LOTTO_PRICE);
        final List<Lotto> randomGeneratedLottos =
                lottoGenerator.generateLottos(this.purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE));
        lottos = new Lottos(randomGeneratedLottos);
    }

    public int getCountOfLottoNumbers() {
        return purchaseAmount.getCountOfLottoNumbers(LOTTO_PRICE);
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final WinningNumbers winningNumbers) {
        return lottos.match(winningNumbers, purchaseAmount);
    }
}
