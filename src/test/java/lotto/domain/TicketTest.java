package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.Money;
import lotto.domain.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @Test
    @DisplayName("티켓 가격 단위 확인")
    void validateTicketPrice() {
        assertThatThrownBy(() -> new Ticket(new Money(12345)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("원 단위");
    }
}
