package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.exception.OverlapException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketsTest {

    @Test
    @DisplayName("중복되는 티켓이 있나 확인하는 테스트")
    void validateOverlap() {
        assertThatThrownBy(() -> {
            new Tickets(
                Arrays.asList(new Ticket("1, 2, 5, 7, 9, 11"), new Ticket("1, 2, 5, 7, 9, 11")));
        }).isInstanceOf(OverlapException.class)
            .hasMessage("중복된 티켓이 있습니다.");
    }
}
