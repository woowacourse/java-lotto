package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.CommonExceptionType;
import exception.LottoExceptionType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("되는 로또")
    void validLotto() {
        Lotto lotto = Lotto.of("1, 2, 3, 4, 5, 6");
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("null이 들어왔을 때 예외 처리된다.")
    void inputWithNull() {
        assertThatThrownBy(() -> Lotto.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("빈 문자열이 들어왔을 때 예외 처리된다.")
    void inputWithBlank() {
        assertThatThrownBy(() -> Lotto.of(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_INPUT_NULL_OR_BLANK.getMessage());
    }

    @Test
    @DisplayName("중복된 숫자가 들어왔을 때 예외 처리된다.")
    void inputWithDuplicate() {
        assertThatThrownBy(() -> Lotto.of("1, 1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoExceptionType.LOTTO_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("문자가 들어왔을 때 예외 처리된다.")
    void inputWithCharacter() {
        assertThatThrownBy(() -> Lotto.of("1, ㅁ, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CommonExceptionType.INVALID_NUMBER_FORMAT.getMessage());
    }

    @ParameterizedTest(name = "{0} 미만이거나 {1} 초과 숫자가 들어왔을 때 예외 처리된다.")
    @MethodSource("provideLottoRange")
    void inputWithInvalidRange(final int lottoMinRange, final int lottoMaxRange) {
        assertThatThrownBy(() -> Lotto.of("48, 1, 2, 3, 4, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoExceptionType.INVALID_LOTTO_RANGE.getMessage(lottoMinRange, lottoMaxRange));
    }

    static Stream<Arguments> provideLottoRange() {
        return Stream.of(Arguments.of(Lotto.LOTTO_MIN_RANGE, Lotto.LOTTO_MAX_RANGE));
    }
}
