package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {
    public static final Money LOTTO_PRICE = new Money(1000);

    private final LottoMachine lottoMachine;

    public LottoStore(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottos buy(Money money) {
        List<Lotto> lottos = new ArrayList<>();
        while (money.isGreaterOrEqualThan(LOTTO_PRICE)) {
            money = money.minus(LOTTO_PRICE);
            lottos.add(lottoMachine.createLotto());
        }

        return new Lottos(lottos);
    }
}
