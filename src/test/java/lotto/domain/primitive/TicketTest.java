package lotto.domain.primitive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketTest {

    @Test
    @DisplayName("티켓을 구매 못하는 경우 확인")
    void validateMinimumTicketPrice() {
        assertThatThrownBy(() -> new Ticket(new Money(900)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("원 이상");
    }
}
