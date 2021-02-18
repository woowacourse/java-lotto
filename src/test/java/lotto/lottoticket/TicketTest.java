package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.lottoticket.TicketValidation.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketTest {
    @Test
    @DisplayName("로또 티켓 생성")
    void ticketCreate() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        new Number("1"),
                        new Number("2"),
                        new Number("3"),
                        new Number("4"),
                        new Number("5"),
                        new Number("6")
                );
        Ticket ticket = new Ticket(numbersGenerator);
        assertThat(ticket).isEqualTo(new Ticket(numbersGenerator));
    }

    @Test
    @DisplayName("1부터 45사이 숫자인지 확인")
    void checkNumberInRange() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        new Number("1"),
                        new Number("46"),
                        new Number("2"),
                        new Number("3"),
                        new Number("4"),
                        new Number("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("로또 숫자 중복 확인")
    void checkDuplicatedNumber() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        new Number("1"),
                        new Number("1"),
                        new Number("2"),
                        new Number("3"),
                        new Number("4"),
                        new Number("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_DUPLICATED);
    }

    @Test
    @DisplayName("로또 숫자 개수 확인")
    void checkSizeOfNumbers() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        new Number("1"),
                        new Number("2"),
                        new Number("3"),
                        new Number("4"),
                        new Number("5")
                );
        assertThatThrownBy(() ->
                new Ticket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_SIZE);
    }

    @Test
    @DisplayName("보너스볼 포함 확인")
    void checkContainBonusBall() {
        NumbersGenerator numbersGenerator =
                () -> Arrays.asList(
                        new Number("1"),
                        new Number("2"),
                        new Number("3"),
                        new Number("4"),
                        new Number("5"),
                        new Number("6")
                );
        BonusBall bonusBall = new BonusBall("6", new WinnerTicket(("1, 2, 3, 4, 5, 8")));
        Ticket ticket = new Ticket(numbersGenerator);
        assertTrue(ticket.hasContainBonus(bonusBall));
    }
}
