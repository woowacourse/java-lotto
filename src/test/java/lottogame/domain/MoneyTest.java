package lottogame.domain;

import lottogame.utils.CannotBuyLottoException;
import lottogame.utils.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @DisplayName("구매 금액이 음수일 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"-14000"})
    void 구매_금액_객체_생성(int input) {
        assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    @DisplayName("로또를 구매할 수 없는 경우(1000원 미만) 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"100", "0", "450"})
    void 로또_구매_기능_테스트(int value) {
        Money money = new Money(value);
        assertThrows(CannotBuyLottoException.class, () -> money.lottoQuantity());
    }

    @DisplayName("로또 구매 개수가 올바르게 출력되는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"14000:14", "1500:1", "3000:3"}, delimiter = ':')
    void 로또_구매_기능_테스트(int value, int expected) {
        Money money = new Money(value);
        assertEquals(expected, money.lottoQuantity());
    }
}
