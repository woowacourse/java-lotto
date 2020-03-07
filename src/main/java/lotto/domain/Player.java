package lotto.domain;

import java.util.List;
import java.util.Set;

public class Player {
    public static List<Lotto> purchaseLottosManually(List<Set<LottoNumber>> lottoNumbersBasket) {
        return LottosGenerator.generateManually(lottoNumbersBasket);
    }

    public static List<Lotto> purchaseLottosAutomatically(Money purchaseAmount, int lottosManualSize) {
        return LottosGenerator.generateAutomatically(purchaseAmount.toLottosSize() - lottosManualSize);
    }
}
