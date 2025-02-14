package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoPaymentTest {

    @Test
    void 금액이_천원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new LottoPayment(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 1,000원 단위로 입력해 주세요.");
    }

    @Test
    void 금액은_0원보다_커야_한다() {
        assertThatThrownBy(() -> new LottoPayment(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 1,000원 이상이여야 합니다.");
    }

    @Test
    void 로또_한개를_구매한다() {
        LottoPayment lottoPayment = new LottoPayment(1000);
        assertThat(lottoPayment.value()).isEqualTo(1000);
    }
}
