package domain.numberscontainer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("티켓 객체 테스트")
public class TicketTest {
    @Test
    @DisplayName("유효한 티켓 생성")
    void ticketConstructor() {
        assertThatThrownBy(() -> new Ticket("1, 2, 3, 4, 5, 5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않는 6개의 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("티켓 생성")
    void compareTwoTickets() {
        Ticket ticket = new Ticket("1, 2, 3, 4, 5, 6");
        assertThat(ticket.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}