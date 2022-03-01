package lotto.domain;

import static lotto.constants.ErrorConstants.ERROR_NULL_MESSAGE;
import static lotto.utils.LottoNumbersGenerator.generateLottoNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private Lottos lottos;

    public void purchase(Money money) {
        Objects.requireNonNull(money, ERROR_NULL_MESSAGE);
        this.lottos = new Lottos(purchaseLottos(money));
    }

    private List<Lotto> purchaseLottos(Money money) {
        List<Lotto> lottoPurchased = new ArrayList<>();
        int purchaseNumber = money.canBuyNumber(new Money(LOTTO_PRICE));
        for (int i = 0; i < purchaseNumber; i++) {
            lottoPurchased.add(new Lotto(generateLottoNumbers()));
        }

        return lottoPurchased;
    }

    public LottoResults confirmWinnings(WinningLotto winningLotto) {
        return new LottoResults(lottos.confirmWinnings(winningLotto));
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
