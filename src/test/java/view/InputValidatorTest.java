package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void test(){
        InputValidator inputValidator = new InputValidator();
        assertThatThrownBy(()-> inputValidator.validateInputMoney("220000000000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }

}