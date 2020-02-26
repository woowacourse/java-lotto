package lotto.util;

import lotto.domain.TicketingCount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class InputValidationUtilTest {

    @Test
    void validateOverTicketingCount_test() {
        TicketingCount ticketingCount = new TicketingCount("1");
        TicketingCount manualTicketingCount = new TicketingCount("2");

        Assertions.assertThatThrownBy(()->
                InputValidationUtil.validateOverTicketingCount(ticketingCount,manualTicketingCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}