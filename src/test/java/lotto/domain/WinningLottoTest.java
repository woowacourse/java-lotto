package lotto.domain;

import lotto.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @DisplayName("올바르지 않은 형식으로 입력했을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1-2-3", "1!2!3", "1 2 3", "1과 2 그리고 3"})
    void invalidTypeInputTest(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("숫자만 입력하시기 바랍니다.");
    }

    @DisplayName("당첨 번호를 6개 입력하지 않았을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void inputCountTest(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageEndingWith("개만 가능합니다.");
    }

    @DisplayName("숫자가 아닌 입력값을 넣었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"일, 이, 삼, 사, 오, 육", "one, two, three, four, five, six", "i, 2, 3, 4, v,6"})
    void notNumberInputTest(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("숫자만 입력하시기 바랍니다.");
    }

    @DisplayName("입력값이 로또 숫자의 범위를 넘었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"0, 1, 2, 3, 4, 5", "41, 42, 43, 44, 45, 46"})
    void rangeTest(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageEndingWith("이하의 숫자만 가능합니다.");
    }

    @DisplayName("중복된 숫자를 입력했을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 1", "23, 36, 40, 41, 36, 17"})
    void duplicatedInputTest(String input) {
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("중복되는 숫자가 존재합니다.");
    }
}
