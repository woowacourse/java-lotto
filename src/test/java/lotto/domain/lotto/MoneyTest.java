package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    @DisplayName("생성 실패 - 0 미만")
    void construct_fail() {
        assertThatThrownBy(() -> new Money(BigInteger.valueOf(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("valueOf 성공")
    @MethodSource("valueOf_success_testcase")
    void valueOf_success(String input, BigInteger expected) {
        assertThat(Money.valueOf(input).toBigInteger()).isEqualTo(expected);
    }

    private static Stream<Arguments> valueOf_success_testcase() {
        return Stream.of(
                Arguments.of("0", new BigInteger("0")),
                Arguments.of("1", new BigInteger("1")),
                Arguments.of(Long.MAX_VALUE + "0", new BigInteger(Long.MAX_VALUE + "0"))
        );
    }

    @ParameterizedTest
    @DisplayName("valueOf 실패 - 0 미만")
    @ValueSource(strings = {"a", "-1", "1.0"})
    void valueOf_fail(String input) {
        assertThatThrownBy(() -> Money.valueOf(input))
                .hasMessage("구입금액은 0 이상의 정수여야합니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("subtract 성공")
    void subtract() {
        Money money = new Money(BigInteger.ONE);

        assertThat(money.subtract(BigInteger.ONE))
                .isEqualTo(new Money(BigInteger.ZERO));
    }

    @Test
    @DisplayName("subtract 실패 - 0미만")
    void subtract_fail() {
        Money zeroMoney = new Money(BigInteger.ZERO);

        assertThatThrownBy(() -> zeroMoney.subtract(BigInteger.ONE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("값 객체 비교")
    @CsvSource(value = {"0", "1000000000000000000000"})
    void equals(String input) {
        Money money1 = new Money(new BigInteger(input));
        Money money2 = new Money(new BigInteger(input));

        assertThat(money1).isEqualTo(money2);
        assertThat(money1).hasSameHashCodeAs(money2);
    }
}