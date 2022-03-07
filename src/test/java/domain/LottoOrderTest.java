package domain;

import exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoOrderTest {

    @DisplayName("로또 티켓은 음수는 불가능하다.")
    @Test
    void validate_ticket_count() {
        assertThatThrownBy(() -> new LottoOrder(new Payment(5000), -1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_TICKET_COUNT.getMessage());
    }

    @DisplayName("수동으로 구매 가능한 로또 티켓 수는 총 구매 티켓 수보다 작아야 한다.")
    @Test
    void validate_manual_ticket_count() {
        assertThatThrownBy(() -> new LottoOrder(new Payment(5000), 7, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_MANUAL_TICKET_COUNT.getMessage());
    }

    @DisplayName("수동 로또 티켓 수와 자동 로또 티켓 수의 합은 총 구매 티켓 수와 같아야 한다.")
    @Test
    void validate_total_ticket_count() {
        assertThatThrownBy(() -> new LottoOrder(new Payment(5000), 3, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_TOTAL_TICKET_COUNT.getMessage());
    }
}
