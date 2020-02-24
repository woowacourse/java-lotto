package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.view.InputView.LOTTO_NUMBERS_DELIMITER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    private static void assertInvokeExceptionWithMessageWhenCreateWinningNumbers(String[] input, String message) {
        assertThatThrownBy(() -> Lotto.from(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith(message);
    }

    @DisplayName("null또는 공백을 입력했을때 예외가 발생하는지 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void nullOrBlankTest(String[] input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "번호를 입력하지 않으셨습니다.");
    }

    @DisplayName("번호를 6개 입력하지 않았을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void inputCountTest(String input) {
        String[] inputs = input.split(LOTTO_NUMBERS_DELIMITER);
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(inputs, "개를 입력하셔야 합니다.");
    }

    @DisplayName("중복된 숫자를 입력했을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 1", "23, 36, 40, 41, 36, 17"})
    void duplicatedInputTest(String input) {
        String[] inputs = input.split(LOTTO_NUMBERS_DELIMITER);
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(inputs, "중복된 숫자가 입력되었습니다.");
    }

    @DisplayName("로또 번호와 당첨 번호가 일치하는 숫자의 개수를 올바르게 계산하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "7, 8, 9, 10, 11, 12:1, 2, 3, 4, 5, 6:0",
            "6, 7, 8, 9, 10, 11:1, 2, 3, 4, 5, 6:1",
            "5, 6, 7, 8, 9, 10:1, 2, 3, 4, 5, 6:2",
            "4, 5, 6, 7, 8, 9:1, 2, 3, 4, 5, 6:3",
            "3, 4, 5, 6, 7, 8:1, 2, 3, 4, 5, 6:4",
            "2, 3, 4, 5, 6, 8:1, 2, 3, 4, 5, 6:5",
            "2, 3, 4, 5, 6, 7:1, 2, 3, 4, 5, 6:5",
            "1, 2, 3, 4, 5, 6:1, 2, 3, 4, 5, 6:6"
    }, delimiter = ':')
    void countSameNumbersTest(String input1, String input2, int expected) {
        Lotto lotto = Lotto.from(input1.split(LOTTO_NUMBERS_DELIMITER));
        Lotto winningLotto = Lotto.from(input2.split(LOTTO_NUMBERS_DELIMITER));
        int result = lotto.countSameNumbers(winningLotto);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("로또 번호와 당첨 번호가 일치하는 숫자의 개수에 따라 올바른 결과값 상금을 만드는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "4, 5, 6, 7, 8, 9:1, 2, 3, 4, 5, 6:7:5000",
            "3, 4, 5, 6, 7, 8:1, 2, 3, 4, 5, 6:7:50000",
            "2, 3, 4, 5, 6, 8:1, 2, 3, 4, 5, 6:7:1500000",
            "2, 3, 4, 5, 6, 7:1, 2, 3, 4, 5, 6:7:30000000",
            "1, 2, 3, 4, 5, 6:1, 2, 3, 4, 5, 6:7:2000000000"
    }, delimiter = ':')
    void createResultTest(String input1, String input2, String input3, int expected) {
        Lotto lotto = Lotto.from(input1.split(LOTTO_NUMBERS_DELIMITER));
        Lotto winningLotto = Lotto.from(input2.split(LOTTO_NUMBERS_DELIMITER));
        LottoNumber bonusNumber = LottoNumber.of(input3);
        MatchResult matchResult = lotto.createResult(winningLotto, bonusNumber);
        assertThat(matchResult.getPrize()).isEqualTo(expected);
    }
}
