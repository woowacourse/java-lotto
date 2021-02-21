package lotto.lottoticket;

import lotto.lottoticket.ticketnumber.NumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.lottoticket.TicketValidation.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 티켓 생성")
    void ticketCreate() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket = new LottoTicket(numbersGenerator);
        assertThat(lottoTicket).isEqualTo(new LottoTicket(numbersGenerator));
    }

    @Test
    @DisplayName("1부터 45사이 숫자인지 확인")
    void checkNumberInRange() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 46, 2, 3, 4, 5);
        assertThatThrownBy(() ->
                new LottoTicket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("로또 숫자 중복 확인")
    void checkDuplicatedNumber() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(() ->
                new LottoTicket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_DUPLICATED);
    }

    @Test
    @DisplayName("로또 숫자 개수 확인")
    void checkSizeOfNumbers() {
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(() ->
                new LottoTicket(numbersGenerator)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_SIZE);
    }

    @Test
    @DisplayName("보너스볼 포함 확인")
    void checkContainBonusBall() {
        BonusBall bonusBall = new BonusBall("6", new WinnerTicket(("1, 2, 3, 4, 5, 8")));
        NumbersGenerator numbersGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTicket lottoTicket = new LottoTicket(numbersGenerator);
        assertTrue(lottoTicket.containsBonusBall(bonusBall));
    }
}
