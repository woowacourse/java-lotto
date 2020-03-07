package lotto.domain;

import java.util.List;
import java.util.Set;

public class Player {
    public static Lottos purchaseLottosManually(List<Set<LottoNumber>> lottoNumbersBasket) {
        return LottosGenerator.generateManually(lottoNumbersBasket);
    }

    public static Lottos purchaseLottosAutomatically(Money purchaseAmount, int lottosManualSize) {
        return LottosGenerator.generateAutomatically(purchaseAmount.toLottosSize() - lottosManualSize);
    }
}
