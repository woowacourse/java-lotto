package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;
    private static final Money LOTTO_MONEY = new Money(LOTTO_PRICE);

    public Lottos buy(Money money, NumberGenerator numberGenerator) {
        validateMoney(money);

        List<Lotto> lottos = new ArrayList<>();
        while (money.isGreaterOrEqualThan(LOTTO_MONEY)) {
            money = money.minus(LOTTO_MONEY);
            lottos.add(Lotto.create(numberGenerator));
        }

        return new Lottos(lottos);
    }

    private void validateMoney(Money money) {
        if (money.remainder(LOTTO_MONEY) != 0) {
            throw new IllegalArgumentException("로또를 사기 위해서는 " + LOTTO_PRICE + "원 단위여야 합니다.");
        }
    }
}
