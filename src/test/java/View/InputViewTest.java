package View;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
    @Test
    void 로또_구매_입력_테스트() {
        String input = "14000";
        Assertions.assertThat(InputView.inputPrice(input)).isEqualTo(14000);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 로또_구매_입력_오류_테스트(String input) {
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력_테스트() {
        String inputWinnerNumber = "1,2,3,4,5,6";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);
        org.junit.jupiter.api.Assertions
                .assertEquals(result, InputView.inputWinnerNumbers(inputWinnerNumber));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"1,2,3,4,5,6,7", "102,1,2,3,4,5", "1;2,3,4,5,6"})
    void 당첨번호_입력_오류_테스트(String input) {
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_볼_입력_테스트() {
        String inputWinnerNumber = "8";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(InputView.inputBonusBall(inputWinnerNumber, result)).
                isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"6", "102"})
    void 보너스_볼_입력_오류_테스트(String input) {
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);
        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(input, result))
                .isInstanceOf(IllegalArgumentException.class);


    }


}