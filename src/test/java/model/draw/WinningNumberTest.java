package model.draw;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumberTest {

    @DisplayName("당첨 번호가 여섯 개가 아닐 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    void outOfCountRangeInWinningNumber(String winningNumberInput) {
        List<Integer> winningNumbers = splitAndParseNumbersToInteger(winningNumberInput);
        assertThatThrownBy(() -> new WinningNumber(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 일 이상 사십오 이하의 범위를 벗어난 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0, 1, 2, 3, 4, 5", "-1, 1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 46"})
    void outOfNumberRangeInWinningNumber(String winningNumberInput) {
        List<Integer> winningNumbers = splitAndParseNumbersToInteger(winningNumberInput);
        assertThatThrownBy(() -> new WinningNumber(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호와 겹치는 로또 번호의 개수를 구한다")
    @ParameterizedTest
    @CsvSource(value = {"1, 7, 8, 9, 10, 11:1", "1, 2, 7, 8, 9, 10:2", "1, 2, 3, 4, 5, 6:6"}, delimiter = ':')
    void findMatchingCountWithLottoNumber(String lottoNumberInput, int expectedCount) {
        List<Integer> lottoNumbers = splitAndParseNumbersToInteger(lottoNumberInput);
        WinningNumber winningNumber = new WinningNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        int matchingCount = winningNumber.findMatchingCountWith(lottoNumbers);

        assertThat(matchingCount).isEqualTo(expectedCount);
    }

    private List<Integer> splitAndParseNumbersToInteger(String numberInput) {
        return Arrays.stream(numberInput.split(", "))
                .map(Integer::parseInt).toList();
    }
}
