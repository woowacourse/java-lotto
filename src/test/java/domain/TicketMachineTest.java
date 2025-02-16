package domain;

import static error.ErrorMessage.INVALID_TICKET_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TicketMachineTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 1234})
    @DisplayName("1000원 단위가 아닐 경우 예외를 발생시킨다.")
    void 구입금액이_1000원_단위가_아닌_경우(int price) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TicketMachine ticketMachine = TicketMachine.create(price);
        });
        assertThat(exception.getMessage()).isEqualTo(INVALID_TICKET_PRICE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 10000})
    @DisplayName("1000원 단위인 경우 성공한다.")
    void 구입금액이_1000원_단위인_경우(int price) {
        int expectedQuantity = price / 1000;
        TicketMachine ticketMachine = TicketMachine.create(price);
        assertEquals(expectedQuantity, ticketMachine.getQuantity());
    }
}
