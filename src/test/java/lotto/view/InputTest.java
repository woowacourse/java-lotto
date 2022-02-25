package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.view.Input.inputPayment;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTest {

    @Test
    @DisplayName("구입 금액에 null 을 입력할 경우")
    void input_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액에 빈값을 입력했을 경우")
    void input_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 문자를 입력했을 경우")
    void input_not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "payment";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}