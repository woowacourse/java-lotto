package utils;

import static org.assertj.core.api.Assertions.*;

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
            Validator.validateRange(999, Integer.MAX_VALUE, 1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위에_해당되면_예외가_발생하지_않는다() {
        assertThatNoException().isThrownBy(() -> {
            Validator.validateRange(10, 100, 1);
        });
    }
}