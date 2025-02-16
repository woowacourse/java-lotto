package utils;

import org.junit.jupiter.api.Test;

import static global.constant.ErrorMessage.NUMERIC_INPUT_ONLY_MESSAGE;
import static global.constant.ErrorMessage.RANGE_INPUT_ONLY_MESSAGE;
import static global.utils.Validator.validateNumberRange;
import static global.utils.Validator.validateNumeric;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    @Test
    void 숫자가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> validateNumeric("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMERIC_INPUT_ONLY_MESSAGE.getMessage());
    }

    @Test
    void 숫자인_경우_예외가_발생하지_않는다() {
        assertThatNoException()
                .isThrownBy(() -> validateNumeric("5"));
    }

    @Test
    void 범위에_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> validateNumberRange(1, Integer.MAX_VALUE, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RANGE_INPUT_ONLY_MESSAGE.getMessage());
    }

    @Test
    void 범위에_해당되면_예외가_발생하지_않는다() {
        assertThatNoException()
                .isThrownBy(() -> validateNumberRange(10, 1, 100));
    }
}
