package lottogame.domain;

import lottogame.utils.InvalidManualTicketQuantityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TicketMachineTest {
    private TicketMachine ticketMachine;

    @BeforeEach
    void setUp() {
        ticketMachine = new TicketMachine();
    }

    @DisplayName("수동 로또 정상 구매 테스트")
    @ParameterizedTest
    @CsvSource(value = {"4000:4:0", "6000:3:3000", "15000:2:13000"}, delimiter = ':')
    void 수동_로또_구매_테스트(int value, int quantity, int expectedMoney) {
        Money money = ticketMachine.validManualTicket(new Money(value), quantity);
        assertThat(money).isEqualTo(new Money(expectedMoney));
    }

    @DisplayName("수동 로또 구매 가능 개수를 초과한 경우")
    @ParameterizedTest
    @CsvSource(value = {"4000:5", "6000:7", "5000:12"}, delimiter = ':')
    void 수동_로또_구매_테스트(int value, int quantity) {
        assertThatThrownBy(() -> ticketMachine.validManualTicket(new Money(value), quantity))
                .isInstanceOf(InvalidManualTicketQuantityException.class);
    }
}