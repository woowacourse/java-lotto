package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    @Test
    @DisplayName("음수의 금액으로 생성하면 에러를 던지는지 확인한다.")
    void checkNegativeMoney() {
        assertThatThrownBy(() -> new LottoMoney(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMoney.NOT_POSITIVE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("티켓 가격의 배수가 아닌 금액으로 로또 구입 금액을 생성할 시 에러를 던지는지 확인한다.")
    void checkNotMultiplesOfPrice() {
        assertThatThrownBy(() -> new LottoMoney(1700))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoMoney.NOT_MULTIPLES_OF_PRICE_ERROR_MESSAGE);
    }
}