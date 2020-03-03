package lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    private Set<LottoNumber> oneToSixLottoNumbers;

    @BeforeEach
    void setUp() {
        oneToSixLottoNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
    }

    @Test
    @DisplayName("LottoTicket을 생성")
    void createLottoTicket() {
        LottoTicket lottoTicket = LottoTicket.from(oneToSixLottoNumbers);

        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("로또 ticket 생성시 중복되지 않은 6개의 숫자가 아니면 예외 발생")
    void createLottoTicketFromDuplicateNumbersThrowsException() {
        Set<LottoNumber> duplicateNumbers = IntStream.rangeClosed(1, 5)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());

        assertThatThrownBy(() -> LottoTicket.from(duplicateNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoTicket은 다른 LottoTicket을 받아서 일치하는 숫자의 개수를 반환")
    void countMathcesWhenMachingFive() {
        LottoTicket oneToSixLottoTicket = LottoTicket.from(oneToSixLottoNumbers);

        Set<LottoNumber> twoToSevenLottoNumbers = IntStream.rangeClosed(2, 7)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        LottoTicket twoToSevenLottoTicket = LottoTicket.from(twoToSevenLottoNumbers);

        assertThat(oneToSixLottoTicket.countMatches(twoToSevenLottoTicket)).isEqualTo(5);
    }

    @Test
    @DisplayName("LottoTicket은 LottoNumber를 받아서 해당하는 값을 갖고 있는지 확인")
    void containsLottoNumber() {
        LottoTicket oneToSixLottoTicket = LottoTicket.from(oneToSixLottoNumbers);
        LottoNumber oneLottoNumber = LottoNumber.from(1);

        assertThat(oneToSixLottoTicket.contains(oneLottoNumber))
                .isTrue();
    }
}
