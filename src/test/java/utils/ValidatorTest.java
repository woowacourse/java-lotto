package utils;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import global.utils.Validator;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void 숫자가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> {
            Validator.validateNumeric("a");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위에_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            Validator.validateNumberRange(999, Integer.MAX_VALUE, 1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위에_해당되면_예외가_발생하지_않는다() {
        assertThatNoException().isThrownBy(() -> {
            Validator.validateNumberRange(10, 1, 100);
        });
    }
}
