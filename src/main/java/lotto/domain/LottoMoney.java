package lotto.domain;

import lotto.util.Count;
import lotto.util.Money;

public class LottoMoney {
    private final Money lottoMoney;

    public LottoMoney(final long lottoMoney) {
        this.lottoMoney = new Money(lottoMoney);
        validateMoneyIsEnough(this.lottoMoney);
    }

    private void validateMoneyIsEnough(Money money) {
        if (money.isLessThen(Lotto.LOTTO_PRICE)) {
            throw new IllegalArgumentException("Money 는 로또 한 장의 금액 이상이어야 합니다.");
        }
    }

    public Count calculateLottoCount() {
        return lottoMoney.divide(Lotto.LOTTO_PRICE);
    }

    public Money getMoney() {
        return this.lottoMoney;
    }
}
