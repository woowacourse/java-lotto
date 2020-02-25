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
        String input = "10";
        Assertions.assertThatCode(() ->new TicketingCount(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "가", " ", ""})
    @DisplayName("TicketingCount 객체가 제대로 생성이 안될 경우")
    void throw_construct_ticket_count(String input) {

        Assertions.assertThatThrownBy(() -> new TicketingCount(input));
    }

    @Test
    @DisplayName("수동 로또 발급 개수와 전체 카운를 빼는 기능")
    void manual_ticketing_count() {
        TicketingCount ticketingCount = new TicketingCount("10");
        TicketingCount input = new TicketingCount("5");
        ticketingCount.calculateCount(input);

        Assertions.assertThat(ticketingCount.getTicketingCount()).isEqualTo(5);
    }
}
