package util;

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

@DisplayName("Dto 생성 테스트")
class SixLottoNumbersFactoryTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        SixLottoNumbersFactory sixLottoNumbersFactory = new SixLottoNumbersFactory();
        assertThatCode(() -> new Ticket(new SixLottoNumbersDTO(sixLottoNumbersFactory.createRandom())))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("고정값 당첨 번호 생성")
    void generateFixedWinningNumber() {
        SixLottoNumbersFactory sixLottoNumbersFactory = new SixLottoNumbersFactory();
        assertThatCode(() -> new WinningNumbers(new SixLottoNumbersDTO(sixLottoNumbersFactory.createFixed(createSixNumbers(1, 2, 3, 4, 5, 6))), new BonusNumberDTO(LottoNumber.SEVEN)))
                .doesNotThrowAnyException();
    }

    private Set<Integer> createSixNumbers(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new HashSet<>(Arrays.asList(number1, number2, number3, number4, number5, number6));
    }
}