package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.vo.Money;

public class Store {

    private static final int LOTTO_PRICE = 1_000;

    private Money leftMoney;

    public Store(Money money) {
        validateUnderLimit(money);
        validateUnderThousands(money);
        this.leftMoney = money;
    }

    public void buyManualLottos(int manualLottoAmount) {
        leftMoney = leftMoney.minus(LOTTO_PRICE * manualLottoAmount);
    }

    public List<Lotto> buyAutoLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            leftMoney = leftMoney.minus(LOTTO_PRICE);
            lottos.add(LottoMachine.generate());
        }
        return lottos;
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(Money.LOTTO_PRICE) || leftMoney.equals(Money.LOTTO_PRICE);
    }

    private void validateUnderLimit(Money money) {
        if (Money.LOTTO_PRICE.isGreaterThan(money)) {
            throw new IllegalArgumentException("입력금액은 1,000원 이상이어야 한다.");
        }
    }

    private void validateUnderThousands(Money money) {
        if (money.hasRemainder(LOTTO_PRICE)) {
            throw new IllegalArgumentException("입력금액은 1,000원 단위어야 한다.");
        }
    }
}
