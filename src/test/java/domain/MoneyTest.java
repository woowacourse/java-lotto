package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("구입 금액이 1_000원 미만이므로 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100, 999})
    void validateRangeTest(final int number) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Money(number)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

    }

    @DisplayName("구입 금액이 1_000원 단위가 아니므로 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1100, 1200, 10001, 21300})
    void validateAmountTest(final int number) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Money(number)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

    }

}
