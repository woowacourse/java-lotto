package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.view.Input.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTest {

    @Test
    @DisplayName("구입 금액에 null 을 입력할 경우")
    void payment_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액에 빈값을 입력했을 경우")
    void payment_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 문자를 입력했을 경우")
    void payment_not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "payment";
            int payment = inputPayment(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 null 을 입력했을 경우")
    void ball_null() {
        assertThatThrownBy(() -> {
            Entering entering = () -> null;
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 빈값을 입력했을 경우")
    void ball_empty() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "";
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 숫자에 문자를 입력했을 경우")
    void ball__not_number() {
        assertThatThrownBy(() -> {
            Entering entering = () -> "ball";
            int ball = inputBonusBall(entering);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}