package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        /* when */
        assertThatThrownBy(() -> LottoNumber.valueOf(number))
                /* then */
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(-1, "로또_숫자_0이하"),
                Arguments.of(46, "로또_숫자_46")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("validParameters")
    @DisplayName("로또 숫자 생성 유효성 검사")
    void validCreate(int number, String testName) {
        /* when */
        assertThatCode(() -> LottoNumber.valueOf(number))
                /* then */
                .doesNotThrowAnyException();
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(1, "로또_숫자_1"),
                Arguments.of(23, "로또_숫자_23"),
                Arguments.of(45, "로또_숫자_45"));
    }
}
