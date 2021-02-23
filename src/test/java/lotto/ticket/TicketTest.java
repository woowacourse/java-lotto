package lotto.ticket;

import lotto.ticket.strategy.ManualNumbersGenerator;
import lotto.ticket.strategy.NumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.ticket.Ticket.ERROR_MESSAGE_DUPLICATED;
import static lotto.ticket.Ticket.ERROR_MESSAGE_INVALID_SIZE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketTest {
    @Test
    @DisplayName("로또 티켓 생성")
    void ticketCreate() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        Number.valueOf("1"),
                        Number.valueOf("2"),
                        Number.valueOf("3"),
                        Number.valueOf("4"),
                        Number.valueOf("5"),
                        Number.valueOf("6")
                );
        Ticket ticket = new Ticket(numbersGenerator.generate());
        assertThat(ticket).isEqualTo(new Ticket(numbersGenerator.generate()));
    }

    @Test
    @DisplayName("1부터 45사이 숫자인지 확인")
    void checkNumberInRange() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        Number.valueOf("1"),
                        Number.valueOf("46"),
                        Number.valueOf("2"),
                        Number.valueOf("3"),
                        Number.valueOf("4"),
                        Number.valueOf("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator.generate())
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("로또 숫자 중복 확인")
    void checkDuplicatedNumber() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        Number.valueOf("1"),
                        Number.valueOf("1"),
                        Number.valueOf("2"),
                        Number.valueOf("3"),
                        Number.valueOf("4"),
                        Number.valueOf("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator.generate())
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_DUPLICATED);
    }

    @Test
    @DisplayName("로또 숫자 개수 확인")
    void checkSizeOfNumbers() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        Number.valueOf("1"),
                        Number.valueOf("2"),
                        Number.valueOf("3"),
                        Number.valueOf("4"),
                        Number.valueOf("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator.generate())
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_SIZE);
    }

    @Test
    @DisplayName("보너스볼 포함 확인")
    void checkContainBonusBall() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        Number.valueOf("1"),
                        Number.valueOf("2"),
                        Number.valueOf("3"),
                        Number.valueOf("4"),
                        Number.valueOf("5"),
                        Number.valueOf("6")
                );
        BonusBall bonusBall = new BonusBall("6", new WinnerTicket(("1, 2, 3, 4, 5, 8")));
        Ticket ticket = new Ticket(numbersGenerator.generate());
        assertTrue(ticket.hasContainBonus(bonusBall));
    }

    @Test
    @DisplayName("수동 생성 시 티켓 내부 숫자 개수 확인")
    void checkManualTicketSize() {
        List<Number> numbers = new ManualNumbersGenerator("1,2,3,4,5,6").generate();
        List<Number> ticket = new Ticket(numbers).getTicket();
        assertThat(ticket.size()).isEqualTo(Ticket.NUMBER_COUNT);
    }
}
