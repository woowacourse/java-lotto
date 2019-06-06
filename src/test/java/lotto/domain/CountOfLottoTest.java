package lotto.domain;

import lotto.exception.NaturalNumberException;
import lotto.exception.PaymentOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CountOfLottoTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("1000");
    }

    @Test
    void 생성자_오류_로또_구입금액이_null일_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new CountOfLotto(null, "1"));
    }

    @Test
    void 생성자_오류_입력받은_수동으로_생성할_로또_개수가_null일_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new CountOfLotto(payment, null));
    }

    @Test
    void 생성자_오류_수동으로_생성할_로또_개수가_총_로또개수보다_많을_경우() {
        assertThatExceptionOfType(PaymentOutOfBoundsException.class)
                .isThrownBy(() -> new CountOfLotto(payment, "2"));
    }

    @Test
    void 생성자_오류_수동으로_생성할_로또_개수가_음수일_경우() {
        assertThatExceptionOfType(NaturalNumberException.class)
                .isThrownBy(() -> new CountOfLotto(payment, "-1"));
    }

    @Test
    void 수동으로_생성할_로또_개수_확인() {
        CountOfLotto countOfLotto = new CountOfLotto(payment, "1");
        assertThat(countOfLotto.getCountOfManualLotto()).isEqualTo(1);
    }

    @Test
    void 자동으로_생성할_로또_개수_확인() {
        CountOfLotto countOfLotto = new CountOfLotto(payment, "1");
        assertThat(countOfLotto.getCountOfRandomLotto()).isEqualTo(0);
    }
}
