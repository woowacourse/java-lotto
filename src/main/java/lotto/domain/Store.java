package lotto.domain;

import lotto.domain.vo.Money;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private static final int OVER_LIMIT_MONEY = 100_000;
    private static final int UNDER_LIMIT_MONEY = 1_000;
    private static final long LOTTO_PRICE = 1_000L;

    private Money leftMoney;

    public Store(int money) {
        validateMoneyRange(money);
        this.leftMoney = new Money(money);
    }

    private void validateMoneyRange(int money) {
        validateOverLimit(money);
        validateUnderLimit(money);
    }

    private void validateOverLimit(int money) {
        if (money > OVER_LIMIT_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0}을 넘을 수 없다.", OVER_LIMIT_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validateUnderLimit(int money) {
        if (money < UNDER_LIMIT_MONEY) {
            String exceptionMessage = MessageFormat.format("입력금액은 {0} 이상이어야 한다.", UNDER_LIMIT_MONEY);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    public List<Lotto> buyLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            lottos.add(buy());
        }
        return lottos;
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(new Money(LOTTO_PRICE));
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(new Money(LOTTO_PRICE));
        return LottoGenerator.generate();
    }
}
