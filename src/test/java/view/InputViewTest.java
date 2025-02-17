package view;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputViewTest {
    @Test
    void 로또_구매_입력_테스트() {
        String input = "14000";
        int expect = 14000;

        int inputPrice = InputView.inputPrice(input);

        Assertions.assertThat(inputPrice).isEqualTo(expect);
    }

    @Test
    void 로또_구매_입력_공백_예외_테스트() {
        String inputEmpty = "";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputEmpty))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_구매_입력_null_예외_테스트() {
        String inputNull = null;
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputNull))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력_테스트() {
        String inputWinnerNumber = "1,2,3,4,5,6";
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> result = InputView.inputWinnerNumbers(inputWinnerNumber);

        org.junit.jupiter.api.Assertions
                .assertEquals(expect, result);
    }

    @Test
    void 당첨번호_공백_예외_테스트() {
        String inputEmpty = "";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputEmpty))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_null_예외_테스트() {
        String inputNull = null;
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputNull))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_추가_입력_예외_테스트() {
        String wrongInputCount = "1,2,3,4,5,6,7";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongInputCount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력_범위_예외_테스트() {
        String wrongInputRange = "46,1,2,3,4,5";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongInputRange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_구분자_예외_테스트() {
        String wrongDelimiter = "1;2,3,4,5,6";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_볼_입력_테스트() {
        String inputWinnerNumber = "8";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertThat(InputView.inputBonusBall(inputWinnerNumber, result)).
                isEqualTo(8);
    }

    @Test
    void 보너스_볼_입력_중복_예외_테스트() {
        String duplicateNumber = "6";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(duplicateNumber, result))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_볼_입력_범위_예외_테스트() {
        String wrongRangeNumber = "46";
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);

        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(wrongRangeNumber, result))
                .isInstanceOf(IllegalArgumentException.class);
    }

}