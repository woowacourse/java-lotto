package lotto.view;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import lotto.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {",", ":", "!", "천5백", "dsad"})
    @DisplayName("돈을 숫자로 입력하지 않으면 에러가 발생한다.")
    void InvalidMoneyInput(String input) {
        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatThrownBy(inputView::readMoney)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MONEY_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "1", "0", "-1"})
    @DisplayName("돈은 숫자로 입력해야 한다.")
    void validMoneyInput(String input) {
        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatCode(inputView::readMoney)
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {",", ":", "!", "천5백", "dsad"})
    @DisplayName("보너스볼을 숫자로 입력하지 않으면 에러가 발생한다.")
    void InvalidBonusNumberInput(String input) {
        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatThrownBy(inputView::readBonusNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ONLY_LOTTO_NUMBER.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "1", "0", "-1"})
    @DisplayName("보너스볼은 숫자로 입력해야 한다.")
    void validBonusNumberInput(String input) {
        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatCode(inputView::readBonusNumber)
                .doesNotThrowAnyException();
    }


    @DisplayName("올바른 당첨번호 입력 테스트")
    @Test
    void correctWinnerNumberInput() {
        String input = "1,2,3,4,5,6";

        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatCode(inputView::readWinnerNumbers)
                .doesNotThrowAnyException();
    }

    @DisplayName("공백을 허용한다.")
    @ParameterizedTest
    @ValueSource(strings = {" 1,2,3,4,5,6", "1,2,3,4,5,6 ", "1, 2,3,4,5,6 "})
    void allowBlankToWinnerNumberInput(String input) {
        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatCode(inputView::readWinnerNumbers)
                .doesNotThrowAnyException();
    }

    @DisplayName("지정한 구분자를 사용해야 한다.")
    @ParameterizedTest
        @ValueSource(strings = {"1.2.3.4.5.6", "1 2 3 4 5 6"})
    void invalidSeparatorToWinnerNumberInput(String input){

        InputView inputView = new InputView(new Scanner(new ByteArrayInputStream(input.getBytes())));

        assertThatThrownBy(inputView::readWinnerNumbers)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.USE_SEPARATOR.getMessage());
    }
}
