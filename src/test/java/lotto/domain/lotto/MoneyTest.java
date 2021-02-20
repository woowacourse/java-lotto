package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    void construct_fail() {
        assertThatThrownBy(() -> new Money(BigInteger.valueOf(-1)));
    }

    @ParameterizedTest
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
    @ValueSource(strings = {"a", "-1", "1.0"})
    void valueOf_fail(String input) {
        assertThatThrownBy(() -> Money.valueOf(input))
                .hasMessage("구입금액은 0 이상의 정수여야합니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equals() {
        Money money1 = new Money(new BigInteger("1"));
        Money money2 = new Money(new BigInteger("1"));

        assertThat(money1).isEqualTo(money2);
    }
}