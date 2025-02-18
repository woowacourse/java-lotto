package view;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    @Test
    @DisplayName("로또 구매 입력 테스트")
    void inputPriceTest() {
        String input = "14000";
        Assertions.assertThat(InputView.inputPrice(input)).isEqualTo(14000);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로또 구매 입력 오류 테스트")
    void inputLottoPriceExceptionTest(String input) {
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호 입력 테스트")
    void inputWinnerNumberTest() {
        String inputWinnerNumber = "1,2,3,4,5,6";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);
        org.junit.jupiter.api.Assertions
                .assertEquals(result, InputView.inputWinnerNumbers(inputWinnerNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"1,2,3,4,5,6,7", "102,1,2,3,4,5", "1;2,3,4,5,6"})
    @DisplayName("당첨번호 입력 오류 테스트")
    void inputWinnerNumberExceptionTest(String input) {
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 볼 입력 테스트")
    void inputBonusBallTest() {
        String inputWinnerNumber = "8";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(InputView.inputBonusBall(inputWinnerNumber, result)).
                isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "45", "46"})
    @DisplayName("보너스 볼 입력 오류 테스트")
    void inputBonusBallExceptionTest(String input) {
        List<Integer> result = List.of(1, 2, 3, 5, 44, 45);
        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(input, result))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
