package strategy;

import domain.numberscontainer.LottoNumber;
import domain.numberscontainer.BonusNumberDTO;
import domain.numberscontainer.SixLottoNumbersDTO;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("티켓 생성 테스트")
class RandomTicketFactoryTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        assertThatCode(() -> RandomTicketFactory.createTicket())
                .doesNotThrowAnyException();
    }
}