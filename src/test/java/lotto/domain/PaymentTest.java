package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {

    @Test
    @DisplayName("구입금액에 0을 입력했을 경우")
    void input_zero() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액에 음수를 입력했을 경우")
    void input_negative() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액이 10원 단위가 아닐 경우")
    void input_not_division_10() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment(1234);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금액이 로또 가격 이하일 경우")
    void input_under_lotto_price() {
        assertThatThrownBy(() -> {
            Payment payment = new Payment(500);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
