package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TicketingCountTest {

    @Test
    @DisplayName("TicketingCount 객체가 제대로 생성될 경우")
    void construct_ticket_count() {
        int input = 10;
        Assertions.assertThatCode(() ->new TicketingCount(input)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("TicketingCount 객체가 제대로 생성이 안될 경우")
    void throw_construct_ticket_count() {
        int input = -1;
        Assertions.assertThatThrownBy(() -> new TicketingCount(input));
    }

    @Test
    @DisplayName("수동 로또 발급 개수를 정상적으로 받은 경우")
    void manual_ticketing_count() {
        TicketingCount ticketingCount = new TicketingCount(10);

        Assertions.assertThatCode(() ->ticketingCount.getManualTicketCount(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "가", " ", ""})
    @DisplayName("수동 로또 발급 개수를 비정상적으로 받은 경우")
    void throw_manual_ticketing_count(String input) {
        TicketingCount ticketingCount = new TicketingCount(10);

        Assertions.assertThatThrownBy(() -> ticketingCount.getManualTicketCount(input));
    }
}
