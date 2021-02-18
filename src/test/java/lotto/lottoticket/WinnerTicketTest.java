package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static lotto.lottoticket.TicketValidation.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinnerTicketTest {
    @Test
    @DisplayName("당첨 번호 생성 확인")
    void winnerTicketCreate() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        assertThat(winnerTicket).isEqualTo(new WinnerTicket("1, 2, 3, 4, 5, 6"));
    }

    @ParameterizedTest
    @DisplayName("잘못된 구분자를 사용한 경우")
    @ValueSource(strings = {"1, 2, 3, 4, 5. 6", "1#2,3,4,5,6", "1,,2,3,4,5,6"})
    void checkWrongDelimiter(String value) {
        assertThatThrownBy(() -> new WinnerTicket(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("숫자가 아닌 경우")
    void checkNotNumber() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("숫자의 범위가 1부터 45사이의 수가 아닌 경우")
    void checkNumberInRange() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void checkNumbersSize() {
        assertThatThrownBy(() -> new WinnerTicket("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_SIZE);
    }

    @Test
    @DisplayName("숫자가 중복되는 경우")
    void checkDuplicated() {
        assertThatThrownBy(() -> new WinnerTicket("1,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_DUPLICATED);
    }

    @Test
    @DisplayName("당첨 결과 확인")
    void checkResult() {
        WinnerTicket winnerTicket = new WinnerTicket("1,2,3,4,5,6");
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1,2,3,4,5,6);
        Ticket ticket = new Ticket(numbersGenerator);
        assertThat(winnerTicket.findMatchCount(ticket)).isEqualTo(6);
    }
}
