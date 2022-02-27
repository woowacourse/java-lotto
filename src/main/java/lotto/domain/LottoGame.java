package lotto.domain;

import static lotto.utils.LottoNumbersGenerator.generateLottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private static final Money LOTTO_PRICE = new Money(1000);

    private Lottos lottos;

    public void purchase(Money money) {
        this.lottos = new Lottos(purchaseLottos(money));
    }

    private List<Lotto> purchaseLottos(Money money) {
        List<Lotto> lottoPurchased = new ArrayList<>();
        int purchaseNumber = money.canBuyNumber(LOTTO_PRICE);
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
