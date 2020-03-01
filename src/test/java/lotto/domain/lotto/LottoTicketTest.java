package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    private LottoNumber numberOne;
    private LottoNumber numberTwo;
    private LottoNumber numberThree;
    private LottoNumber numberFour;
    private LottoNumber numberFive;
    private LottoNumber numberSix;

    @BeforeEach
    void setUp() {
        numberOne = new LottoNumber(1);
        numberTwo = new LottoNumber(2);
        numberThree = new LottoNumber(3);
        numberFour = new LottoNumber(4);
        numberFive = new LottoNumber(5);
        numberSix = new LottoNumber(6);
    }


    @Test
    @DisplayName("LottoTicket을 생성")
    void createLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(numberOne);
        numbers.add(numberTwo);
        numbers.add(numberThree);
        numbers.add(numberFour);
        numbers.add(numberFive);
        numbers.add(numberSix);
        assertThat(new LottoTicket(numbers)).isNotNull();
    }

    @Test
    @DisplayName("Lottoticket 생성시 중복되지 않은 6개의 숫자가 아니면 예외 발생")
    void createLottoTicketFromDuplicateNumbersThrowsException() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(numberOne);
        numbers.add(numberTwo);
        numbers.add(numberThree);
        numbers.add(numberFour);
        numbers.add(numberFive);
        numbers.add(numberFive);

        assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoTicket은 다른 LottoTicket을 받아서 일치하는 숫자의 개수를 반환")
    void countMatchesWhenMatchingFive() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(numberOne);
        numbers.add(numberTwo);
        numbers.add(numberThree);
        numbers.add(numberFour);
        numbers.add(numberFive);
        numbers.add(numberSix);
        LottoTicket lottoTicket = new LottoTicket(numbers);

        Set<LottoNumber> targetNumbers = new HashSet<>();
        LottoNumber numberSeven = new LottoNumber(7);
        targetNumbers.add(numberOne);
        targetNumbers.add(numberTwo);
        targetNumbers.add(numberThree);
        targetNumbers.add(numberFour);
        targetNumbers.add(numberFive);
        targetNumbers.add(numberSeven);
        LottoTicket targetLottoTicket = new LottoTicket(targetNumbers);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }
}
