package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @Test
    @DisplayName("LottoTicket을 생성")
    void createLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(LottoNumber.from(1));
        numbers.add(LottoNumber.from(2));
        numbers.add(LottoNumber.from(3));
        numbers.add(LottoNumber.from(4));
        numbers.add(LottoNumber.from(5));
        numbers.add(LottoNumber.from(6));
        LottoTicket lottoTicket = LottoTicket.from(numbers);

        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("로또 ticket 생성시 중복되지 않은 6개의 숫자가 아니면 예외 발생")
    void createLottoTicketFromDuplicateNumbersThrowsException() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(LottoNumber.from(1));
        numbers.add(LottoNumber.from(2));
        numbers.add(LottoNumber.from(3));
        numbers.add(LottoNumber.from(4));
        numbers.add(LottoNumber.from(5));
        numbers.add(LottoNumber.from(5));

        assertThatThrownBy(() -> LottoTicket.from(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoTicket은 다른 LottoTicket을 받아서 일치하는 숫자의 개수를 반환")
    void countMathcesWhenMachingFive() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(LottoNumber.from(1));
        numbers.add(LottoNumber.from(2));
        numbers.add(LottoNumber.from(3));
        numbers.add(LottoNumber.from(4));
        numbers.add(LottoNumber.from(5));
        numbers.add(LottoNumber.from(6));
        LottoTicket lottoTicket = LottoTicket.from(numbers);

        Set<LottoNumber> targetNumbers = new HashSet<>();
        targetNumbers.add(LottoNumber.from(1));
        targetNumbers.add(LottoNumber.from(2));
        targetNumbers.add(LottoNumber.from(3));
        targetNumbers.add(LottoNumber.from(4));
        targetNumbers.add(LottoNumber.from(5));
        targetNumbers.add(LottoNumber.from(7));
        LottoTicket targetLottoTicket = LottoTicket.from(targetNumbers);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }
}
