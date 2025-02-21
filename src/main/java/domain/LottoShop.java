package domain;

import java.util.ArrayList;
import java.util.List;
import util.RandomNumbersGenerator;

public class LottoShop {
    public LottoWallet buyLottos(final Money money) {
        final int purchasableLottoCount = Lotto.calculatePurchasableLottoCount(money);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchasableLottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(LottoNumber.MIN, LottoNumber.MAX, 6)));
        }
        return new LottoWallet(lottos);
    }
}
