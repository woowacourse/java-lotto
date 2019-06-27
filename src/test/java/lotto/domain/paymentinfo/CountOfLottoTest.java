package lotto.domain.paymentinfo;

import lotto.domain.exception.NaturalNumberException;
import lotto.domain.exception.PaymentOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class CountOfLottoTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(5000);
    }

    @Test
    void 수동_로또_개수가_금액을_초과할_경우() {
        assertThatThrownBy(() -> new CountOfLotto(payment, 6)).isInstanceOf(PaymentOutOfBoundsException.class);
    }

    @Test
    void 수동_로또_개수가_음수일_경우() {
        assertThatThrownBy(() -> new CountOfLotto(payment, -1)).isInstanceOf(NaturalNumberException.class);
    }
}
