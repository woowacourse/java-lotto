package model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumberTest {

    @DisplayName("당첨 번호는 입력값을 구분자로 나누어 숫자로 저장한다")
    @Test
    void parseWinningNumberInput() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        for(int i = 1; i <= 6; i++) {
            assertThat(winningNumber.contains(i)).isTrue();
        }
    }

    @DisplayName("당첨 번호는 여섯 개의 숫자로 이루어져 있다")
    @Test
    void containsSixNumberInWinningNumber() {
        assertDoesNotThrow(() -> new WinningNumber("1, 2, 3, 4, 5, 6"));
    }

    @DisplayName("당첨 번호가 여섯 개가 아닐 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void outOfCountRangeInWinningNumber(String winningNumberInput) {
        assertThatThrownBy(() -> new WinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 일 이상 사십오 이하의 범위를 벗어난 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0, 1, 2, 3, 4, 5", "-1, 1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 46"})
    void outOfNumberRangeInWinningNumber(String winningNumberInput) {
        assertThatThrownBy(() -> new WinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 정수가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a, 1, 2, 3, 4, 5", "@, 1, 2, 3, 4, 5", "6.0, 1, 2, 3, 4, 5", " , 1, 2, 3, 4, 5", ", 1, 2, 3, 4, 5"})
    void notIntegerLottoNumber(String winningNumberInput) {
        assertThatThrownBy(() -> new WinningNumber(winningNumberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 겹치는 로또 번호의 개수를 구한다")
    @ParameterizedTest
    @CsvSource(value = {"1, 7, 8, 9, 10, 11:1", "1, 2, 7, 8, 9, 10:2", "1, 2, 3, 4, 5, 6:6"}, delimiter = ':')
    void findMatchingCountWithLottoNumber(String lottoNumberInput, int expectedCount) {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        int matchingCount = winningNumber.findMatchingCountWith(
                                    Arrays.stream(lottoNumberInput.split(", "))
                                    .map(Integer::parseInt).toList());

        assertThat(matchingCount).isEqualTo(expectedCount);
    }


}