package lottogame.domain.lotto;

import lottogame.domain.lotto.Money;
import lottogame.utils.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("구매 금액이 음수일 경우 예외 처리")
    @Test
    void 구매_금액_객체_생성() {
        assertThatThrownBy(() -> {
            new Money(-14000);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    @DisplayName("구매 금액이 정상 입력된 경우")
    @Test
    void 구매_금액_객체_생성2() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }
}
