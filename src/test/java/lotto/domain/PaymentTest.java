package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.InvalidPaymentAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @DisplayName("천단위 절삭 테스트")
    @Test
    void create() {
        Payment payment = new Payment(14500);

        assertThat(payment).isEqualTo(new Payment(14000));
    }

    @DisplayName("구매한 티켓 개수 테스트")
    @Test
    void count() {
        Payment payment = new Payment(12312);

        assertThat(payment.count()).isEqualTo(12);
    }

    @DisplayName("천단위 미만 에러 테스트")
    @Test
    void exception() {
        assertThatThrownBy(() ->
            new Payment(999)
        ).isInstanceOf(InvalidPaymentAmountException.class);
    }

}
