package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {


    @Test
    @DisplayName("LottoTicket을 생성")
    void createLottoTicket() {
        Set<Integer> numbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        assertThat(new LottoTicket(numbers)).isNotNull();
    }

    @Test
    @DisplayName("Lottoticket 생성시 중복되지 않은 6개의 숫자가 아니면 예외 발생")
    void createLottoTicketFromDuplicateNumbersThrowsException() {
        Set<Integer> numbers = IntStream.of(1,2,3,4,5,5).boxed().collect(Collectors.toSet());

        assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoTicket은 다른 LottoTicket을 받아서 일치하는 숫자의 개수를 반환")
    void countMatchesWhenMatchingFive() {
        Set<Integer> numbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        LottoTicket lottoTicket = new LottoTicket(numbers);

        Set<Integer> targetNumbers = IntStream.of(1,2,3,4,5,7).boxed().collect(Collectors.toSet());
        LottoTicket targetLottoTicket = new LottoTicket(targetNumbers);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }
}
