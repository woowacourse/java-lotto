package utils;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.utils.Validator;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    void 범위에_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> {
            Validator.validateRange(999, Integer.MAX_VALUE, 1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위에_해당되면_예외가_발생하지_않는다() {
        assertThatNoException().isThrownBy(() -> {
            Validator.validateRange(10, 1, 100);
        });
    }

    @Test
    void 금액이_1000원_단위면_예외가_발생하지_않는다() {
        assertThatNoException().isThrownBy(() -> {
            Validator.validateDivisibility(5000, 1000);
        });
    }

    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy((() -> {
            Validator.validateDivisibility(5300, 1000);
        }));
    }
}