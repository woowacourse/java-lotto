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

    @DisplayName("로또 숫자 하나의 포함 여부를 올바르게 반환하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1:true", "6:true", "7:false"}, delimiter = ':')
    void containsTest(int input, boolean expected) {
        Lotto lotto = Lotto.from(new String[]{"1", "2", "3", "4", "5", "6"});
        LottoNumber lottoNumber = LottoNumber.of(input);
        boolean result = lotto.contains(lottoNumber);
        assertThat(result).isEqualTo(expected);
    }
}
