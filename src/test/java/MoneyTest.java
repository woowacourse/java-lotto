import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @ParameterizedTest
    @DisplayName("Money를 생성할 때 금액이 양수가 아니면 예외가 발생한다.")
    @CsvSource(value = {"-1000", "0"})
    void exception_argv_is_not_positive_when_ctor(final int input) {
        // when & then
        assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("정적 생성자로 Money 생성할 때 숫자로만 구성된 문자열이면 예외가 발생하지 않는다")
    @CsvSource(value = {"100", "1000", "5000", "10000"})
    void not_exception_argv_is_only_number_string_when_static_ctor(final String input) {
        // when
        Money money = Money.of(input);

        // then
        assertThat(money.getValue()).isEqualTo(Integer.parseInt(input));
    }

    @Test
    @DisplayName("정적 생성자로 Money 생성할 때 숫자 이외 문자열이 포함되면 예외가 발생한다")
    void exception_argv_is_not_only_number_string_when_static_ctor() {
        // given
        final String input = "woowa";

        // when & then
        assertThatThrownBy(() -> {
            Money.of(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}