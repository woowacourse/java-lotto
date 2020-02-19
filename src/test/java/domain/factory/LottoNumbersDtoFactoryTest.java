package domain.factory;

import domain.LottoNumber;
import domain.numberscontainer.LottoNumbersDto;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottoNumbersDtoFactoryTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory();
        assertThatCode(() -> new Ticket(lottoNumberFactory.generateRandomTicketDto()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("고정값 당첨 번호 생성")
    void generateFixedWinningNumber() {
        LottoNumberFactory lottoNumberFactory = new LottoNumberFactory();
        assertThatCode(() -> new WinningNumbers(lottoNumberFactory.generateFixedNumber(createSixNumbers(1, 2, 3, 4, 5, 6), LottoNumber.SEVEN)))
                .doesNotThrowAnyException();
    }

    private Set<LottoNumber> createSixNumbers(int number1, int number2, int number3, int number4, int number5, int number6) {
        Set<LottoNumber> sixNumbers = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(number1),
                LottoNumber.getLottoNumber(number2),
                LottoNumber.getLottoNumber(number3),
                LottoNumber.getLottoNumber(number4),
                LottoNumber.getLottoNumber(number5),
                LottoNumber.getLottoNumber(number6)));

        return sixNumbers;
    }
}