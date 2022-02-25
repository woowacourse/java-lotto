package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {

    @Test
    @DisplayName("Null값을 입력했을 경우")
    void input_null() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈값을 입력했을 경우")
    void input_empty() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 문자를 입력했을 경우")
    void input_not_number() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("lotto");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 0을 입력했을 경우")
    void input_zero() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("0");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 음수를 입력했을 경우")
    void input_negative() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("-1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액이 1,000원 단위로 나뉘지 않을 경우")
    void input_not_division_1000() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment("12345");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
