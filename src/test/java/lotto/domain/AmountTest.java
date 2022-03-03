package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AmountTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("투입금액 생성 유효성 검사")
    void invalidCreate(int amount, String testName) {
        assertThatThrownBy(() -> new Amount(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(0, "투입금액 0"),
            Arguments.of(100, "천원단위로 안나누어떨어질때"),
            Arguments.of(-1, "투입금액 음수")
        );
    }

    @Test
    @DisplayName("투입금액이 10000원일땐 예외를 던지지 않아야 합니다.")
    void amountValidTest() {
        assertThatCode(() -> new Amount(10000))
            .doesNotThrowAnyException();
    }
}
