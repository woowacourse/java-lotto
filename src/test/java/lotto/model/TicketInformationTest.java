package lotto.model;

import lotto.exception.OverRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicketInformationTest {
    @Test
    void checkOverPay() {
        assertThatThrownBy(() -> {
            TicketInformation ticketInformation = new TicketInformation(10, 12);
        }).isInstanceOf(OverRangeException.class)
        .hasMessage("금액의 한도를 초과하였습니다.");
    }
}
