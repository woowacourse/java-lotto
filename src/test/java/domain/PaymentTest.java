package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {

    @DisplayName("로또 가격보다 크거나 같은 경우 성공")
    @Test
    void input_more_than_lotto_price() {
        assertThatCode(() -> new Payment(1000))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 가격보다 작은 경우 실패")
    @Test
    void input_less_than_lotto_price() {
        assertThatThrownBy(() -> new Payment(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액이 로또 가격보다 작습니다.");
    }

    @DisplayName("구입금액 10만원 초과 실패")
    @Test
    void range_max() {
        assertThatThrownBy(() -> new Payment(110000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 한사람 당 10만원씩만 살 수 있습니다.");
    }

    @DisplayName("로또 생성 횟수 계산")
    @Test
    void calculate_lotto_count() {
        assertThat(new Payment(14500).calculateLottoCount()).isEqualTo(14);
    }

    @DisplayName("로또 총 구매 가격 계산")
    @Test
    void calculate_ticket_payment() {
        assertThat(new Payment(14500).calculateTicketPayment()).isEqualTo(14000);
    }
}
