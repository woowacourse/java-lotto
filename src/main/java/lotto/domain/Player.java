package lotto.domain;

import java.util.List;
import java.util.Set;

public class Player {
    public List<Lotto> purchaseLottosManually(List<Set<LottoNumber>> lottoNumbersBasket) {
        return LottosGenerator.generateManually(lottoNumbersBasket);
    }

    public List<Lotto> purchaseLottosAutomatically(Money purchaseAmount, int lottosManualSize) {
        return LottosGenerator.generateAutomatically(purchaseAmount.toLottosSize() - lottosManualSize);
    }
}
