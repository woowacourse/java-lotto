package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @Nested
    @DisplayName("구입금액은")
    class NewMoney {

        @Nested
        @DisplayName("1000 ~ 20억 사이의 값이 아닌 경우")
        class Context_without_range_1000_to_2billions {

            @ParameterizedTest
            @ValueSource(strings = {"999", "2000000001"})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(int value) {
                assertThatThrownBy(() -> new Money(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1000부터 20억의 숫자여야 합니다.");
            }
        }

        @Nested
        @DisplayName("1000으로 나누어지는 값이 아니라면")
        class Context_with_not_divided_by_1000 {

            @ParameterizedTest
            @ValueSource(strings = {"1001", "2100"})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(int value) {
                assertThatThrownBy(() -> new Money(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1000으로 나누어 떨어져야 합니다.");
            }
        }
    }
}
