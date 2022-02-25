package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Money;

public class Store {

    private static final Money UNDER_LIMIT_MONEY = new Money(1_000L);
    private static final Money LOTTO_PRICE = new Money(1_000L);

    private Money leftMoney;

    public Store(Money money) {
        validateUnderLimit(money);
        validateUnderThousands(money);
        this.leftMoney = money;
    }

    public List<Lotto> buyLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            lottos.add(buy());
        }
        return lottos;
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(LOTTO_PRICE) || leftMoney.equals(LOTTO_PRICE);
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(LOTTO_PRICE);
        return LottoMachine.generate();
    }

    private void validateUnderLimit(Money money) {
        if (UNDER_LIMIT_MONEY.isGreaterThan(money)) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

    private void validateUnderThousands(Money money) {
        if (money.hasRemainder(LOTTO_PRICE)) {
            throw new IllegalArgumentException("입력금액은 1,000원 단위어야 한다.");
        }
    }
}
