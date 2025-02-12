package View;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputViewTest {
    @Test
    void 로또_구매_입력_테스트(){
        String input = "14000";
        Assertions.assertThat(InputView.inputPrice(input)).isEqualTo(14000);
    }

    @Test
    void 로또_구매_입력_오류_테스트(){
        String inputEmpty = "";
        String inputNull = null;
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputEmpty))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputNull))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_입력_테스트(){
        String inputWinnerNumber = "1,2,3,4,5,6";
        List<Integer> result = List.of(1,2,3,4,5,6);
        org.junit.jupiter.api.Assertions
                .assertEquals(result, InputView.inputWinnerNumbers(inputWinnerNumber));
    }

    @Test
    void 당첨번호_입력_오류_테스트(){
        String inputEmpty = "";
        String inputNull = null;
        String wrongInputCount = "1,2,3,4,5,6,7";
        String wrongInputRange = "102,1,2,3,4,5";
        String wrongDelimiter = "1;2,3,4,5,6";
        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputEmpty))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputPrice(inputNull))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongInputCount))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongInputRange))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputPrice(wrongDelimiter))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_볼_입력_테스트(){
        String inputWinnerNumber = "8";
        List<Integer> result = List.of(1,2,3,4,5,6);

        Assertions.assertThat(InputView.inputBonusBall(inputWinnerNumber, result)).
                isEqualTo(8);
    }

    @Test
    void 보너스_볼_입력_오류_테스트(){
        String duplicateNumber = "6";
        String wrongRangeNumber = "102";
        List<Integer> result = List.of(1,2,3,4,5,6);

        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(duplicateNumber, result))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> InputView.inputBonusBall(wrongRangeNumber, result))
                .isInstanceOf(IllegalArgumentException.class);


    }





}