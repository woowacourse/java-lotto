package lotto.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.ticket.Ticket.ERROR_MESSAGE_DUPLICATED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusBallTest {
    @Test
    @DisplayName("보너스 볼 생성")
    void bonusBallCreate() {
        BonusBall bonusBall = new BonusBall("10", new WinnerTicket("1, 2, 3, 4, 5, 6"));
        assertThat(bonusBall).isEqualTo(new BonusBall("10", new WinnerTicket("1, 2, 3, 4, 5, 6")));
    }

    @Test
    @DisplayName("숫자가 아닌 경우")
    void bonusBallNotNumber() {
        assertThatThrownBy(() ->
                new BonusBall("*", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("숫자의 범위가 1부터 45사이의 수가 아닌 경우")
    void checkNumberInRange() {
        assertThatThrownBy(() -> new BonusBall("46", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("숫자가 중복되는 경우")
    void checkDuplicated() {
        assertThatThrownBy(() -> new BonusBall("6", new WinnerTicket("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_DUPLICATED);
    }

    @Test
    @DisplayName("보너스볼 포함 확인")
    void checkContainBonusBall() {
        BonusBall bonusBall = new BonusBall("15", new WinnerTicket("1, 2, 3, 4, 5, 6"));
        assertTrue(bonusBall.isSameThan(Number.valueOf("15")));
        assertFalse(bonusBall.isSameThan(Number.valueOf("5")));
    }
}
