package model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.NumberValidator;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

    @Test
    void 입력값이_숫자가_아니라면_예외를_발생시킨다() {
        String input = "숫자아님";
        assertThatThrownBy(
                () -> NumberValidator.validateInteger(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력값이_음수라면_예외를_발생시킨다() {
        int inputNumber = -1;
        assertThatThrownBy(
                () -> NumberValidator.validatePositive(inputNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력값이_0이라면_예외를_발생시킨다() {
        int inputNumber = 0;
        assertThatThrownBy(
                () -> NumberValidator.validatePositive(inputNumber)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력값이_정수라면_예외를_발생시키지_않는다() {
        String input = "1";
        assertThatCode(
                () -> NumberValidator.validateInteger(input)
        ).doesNotThrowAnyException();
    }

    @Test
    void 입력값이_양의_정수라면_예외를_발생시키지_않는다() {
        int input = 3;
        assertThatCode(
                () -> NumberValidator.validatePositive(input)
        ).doesNotThrowAnyException();
    }
}
