package domain;

import domain.lottogeneratestrategy.LottoPickStrategy;
import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;

    private final LottoPickStrategy lottoPickStrategy;

    public LottoStore(LottoPickStrategy lottoPickStrategy) {
        this.lottoPickStrategy = lottoPickStrategy;
    }

    public static Money calculatePurchaseAmount(int lottoCount) {
        return new Money(lottoCount * LOTTO_PRICE);
    }

    public Lottos buy(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        Money lottoPrice = new Money(LOTTO_PRICE);
        while (money.isGreaterOrEqualThan(lottoPrice)) {
            money = money.minus(lottoPrice);
            lottos.add(Lotto.createRandomLotto(lottoPickStrategy));
        }

        return new Lottos(lottos);
    }

    public boolean isPurchasable(int amount) {
        return LOTTO_PRICE <= amount;
    }
}
