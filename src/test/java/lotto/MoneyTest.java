package lotto;

import lotto.domain.Money;
import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void 최소값보다_적은_금액일_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            Money.generatePurchaseMoney(999);
        }).isInstanceOf(MoneyException.class)
                .hasMessage("구매금액은 1000원 이상이어야 합니다.");
    }
}
