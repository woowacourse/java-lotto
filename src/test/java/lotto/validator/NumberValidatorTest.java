package lotto.validator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberValidatorTest {

    @Test
    @DisplayName("Null값을 입력했을 경우")
    void input_null() {
        assertThatThrownBy(() -> {
            NumberValidator.validateNumber(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈값을 입력했을 경우")
    void input_empty() {
        assertThatThrownBy(() -> {
            NumberValidator.validateNumber("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("문자를 입력했을 경우")
    void input_not_number() {
        assertThatThrownBy(() -> {
            NumberValidator.validateNumber("number");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}