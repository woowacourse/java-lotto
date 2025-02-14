package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    void 입력받은_돈이_int범위를_벗어나는_경우_예외를_반환한다() {
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(() -> inputValidator.validateInputMoney(String.valueOf((long) Integer.MAX_VALUE + 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "1s"})
    void 입력받은_돈이_숫자가_아닌_경우_예외를_반환한다(String input) {
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(() -> inputValidator.validateInputMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"s", "1s"})
    void 입력받은_값이_숫자가_아닌_경우_예외를_반환한다(String input) {
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(() -> inputValidator.validateNotStringNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }


    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,a"})
    void 당첨번호가_숫자가_아닌_경우_예외를_반환한다(String input) {
        //given
        InputValidator inputValidator = new InputValidator();
        //when //then
        assertThatThrownBy(() -> inputValidator.validateWinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");

    }


}