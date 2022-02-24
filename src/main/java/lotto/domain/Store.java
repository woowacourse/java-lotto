package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private static final int OVER_LIMIT_MONEY = 100_000;
    private static final int UNDER_LIMIT_MONEY = 1_000;
    private static final long LOTTO_PRICE = 1_000L;

    private Money leftMoney;

    public Store(int money) {
        validateOverLimit(money);
        validateUnderLimit(money);
        this.leftMoney = new Money(money);
    }

    public List<Lotto> buyLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            lottos.add(buy());
        }
        return lottos;
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(new Money(LOTTO_PRICE));
        return LottoMachine.generate();
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(new Money(LOTTO_PRICE));
    }

    private void validateOverLimit(int money) {
        if (money > OVER_LIMIT_MONEY) {
            throw new IllegalArgumentException("입력금액은 100,000원을 넘을 수 없다.");
        }
    }

    private void validateUnderLimit(int money) {
        if (money < UNDER_LIMIT_MONEY) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }
}
