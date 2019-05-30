package lotto.domain;

import lotto.InputValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidatorTest {
    @Test
    void 로또가격_공백() {
        assertThat(InputValidator.isNotValidPrice(" ")).isTrue();
    }

    @Test
    void 로또가격_음수() {
        assertThat(InputValidator.isNotValidPrice("-1")).isTrue();
    }

    @Test
    void 로또가격_천원단위아닐때() {
        assertThat(InputValidator.isNotValidPrice("1100")).isTrue();
    }

}
