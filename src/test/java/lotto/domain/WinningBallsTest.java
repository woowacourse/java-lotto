package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningBallsTest {
    @DisplayName("null또는 공백을 입력했을때 예외가 발생하는지 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void nullOrBlankTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("당첨 번호를 입력하지 않으셨습니다.");
    }

    @DisplayName("구분자를 입력하지 않았을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1-2-3", "123", "1 2 3", "1과 2 그리고 3"})
    void noDelimiterInputTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("나누어 입력해 주세요.");
    }

    @DisplayName("당첨 번호를 6개 입력하지 않았을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void inputCountTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("개만 가능합니다.");
    }

    @DisplayName("숫자가 아닌 입력값을 넣었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"일, 이, 삼, 사, 오, 육", "one, two, three, four, five, six", "i, 2, 3, 4, v,6"})
    void notNumberInputTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("숫자만 입력하시기 바랍니다.");
    }

    @DisplayName("입력값이 로또 숫자의 범위를 넘었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"0, 1, 2, 3, 4, 5", "41, 42, 43, 44, 45, 46"})
    void exceedRangeInputTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("이하의 숫자만 입력 가능합니다.");
    }

    @DisplayName("중복된 숫자를 입력했을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 1", "23, 36, 40, 41, 36, 17"})
    void duplicatedInputTest(String input) {
        assertThatThrownBy(() -> new WinningBalls(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("중복된 숫자가 입력되었습니다.");
    }
}
