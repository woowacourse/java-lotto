package lotto;

import lotto.domain.Money;
import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @DisplayName("최소값보다 적은 금액일 경우 예외 발생")
    @Test
    void minimumMoney() {
        Assertions.assertThatThrownBy(() -> {
            Money.createPurchaseMoney(999);
        }).isInstanceOf(MoneyException.class)
                .hasMessage("구매금액은 1000원 이상이어야 합니다.");
    }
}
