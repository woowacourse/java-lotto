package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumberTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("로또 숫자 생성 유효성 실패 검사")
    void invalidCreate(int number, String testName) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(-1, "로또 숫자 음수"),
            Arguments.of(0, "로또 숫자 0"),
            Arguments.of(46, "로또 숫자 46"),
            Arguments.of(100, "로또 숫자 100")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("validParameters")
    @DisplayName("로또 숫자 생성 유효성 검사")
    void validCreate(int number, String testName) {
        assertThatCode(() -> new LottoNumber(number))
            .doesNotThrowAnyException();
    }

    private static Stream<Arguments> validParameters() {
        return Stream.of(
            Arguments.of(1, "로또 숫자 1"),
            Arguments.of(23, "로또 숫자 23"),
            Arguments.of(45, "로또 숫자 45"));
    }
}
