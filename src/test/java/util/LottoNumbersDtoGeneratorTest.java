package util;

import domain.LottoNumber;
import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Dto 생성 테스트")
class LottoNumbersDtoGeneratorTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        LottoNumbersDtoGenerator lottoNumbersDtoGenerator = new LottoNumbersDtoGenerator();
        assertThatCode(() -> new Ticket(lottoNumbersDtoGenerator.generateRandomTicketDto()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("고정값 당첨 번호 생성")
    void generateFixedWinningNumber() {
        LottoNumbersDtoGenerator lottoNumbersDtoGenerator = new LottoNumbersDtoGenerator();
        assertThatCode(() -> new WinningNumbers(lottoNumbersDtoGenerator.generateFixedNumberDto(createSixNumbers(1, 2, 3, 4, 5, 6), 7)))
                .doesNotThrowAnyException();
    }

    private Set<Integer> createSixNumbers(int number1, int number2, int number3, int number4, int number5, int number6) {
        return new HashSet<>(Arrays.asList(number1, number2, number3, number4, number5, number6));
    }
}