package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {

    @Test
    @DisplayName("당첨번호를 생성한다")
    void makeWinningNumber() {
        String input = "1, 2, 3, 4, 5, 6";

        Set<LottoNumber> expected = new HashSet<>(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));

        WinningNumber winningNumber = new WinningNumber(input);

        Assertions.assertThat(winningNumber.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨번호가 중복될 경우 예외를 발생시킨다")
    void throwExceptionWhenDuplicate() {
        String input = "1, 2, 3, 5, 5, 6";

        Assertions.assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    @DisplayName("당첨번호의 개수가 6이 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInvalidInputSize(String input) {
        Assertions.assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("당첨번호가 empty, blank일 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsEmptyOrBlank(String input) {
        assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
