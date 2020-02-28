package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketsTest {

    @Test
    @DisplayName("티켓들 생성")
    void generateRandomTicket() {
        assertThat(Tickets.createTickets(2, 2,
                Arrays.asList("1, 2, 3, 4, 5, 6", "1, 2, 3, 7, 8, 9")).toString()).isEqualTo("[1, 2, 3, 4, 5, 6]" + System.lineSeparator() + "[1, 2, 3, 7, 8, 9]");
    }

    @Test
    @DisplayName("수동 티켓 사이즈 검증")
    void test1() {
        assertThatThrownBy(() -> Tickets.createTickets(5, 10, Arrays.asList("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 5장 구매 가능합니다.");
    }

    @Test
    @DisplayName("수동 티켓 사이즈 검증")
    void test2() {
        assertThatThrownBy(() -> Tickets.createTickets(5, -1, Arrays.asList("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 0장 이상 구매 가능합니다.");
    }
}
