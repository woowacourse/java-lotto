package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("LottoTicket을 생성")
    void createLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));
        LottoTicket lottoTicket = new LottoTicket(numbers);

        assertThat(lottoTicket);
    }

    @Test
    @DisplayName("Lottoticket 생성시 중복되지 않은 6개의 숫자가 아니면 예외 발생")
    void createLottoTicketFromDuplicateNumbersThrowsException() {
        Set<LottoNumber> numbers = new HashSet<>();
        numbers.add(LottoFactory.publishLottoNumberFrom(1));
        numbers.add(LottoFactory.publishLottoNumberFrom(2));
        numbers.add(LottoFactory.publishLottoNumberFrom(3));
        numbers.add(LottoFactory.publishLottoNumberFrom(4));
        numbers.add(LottoFactory.publishLottoNumberFrom(5));
        numbers.add(LottoFactory.publishLottoNumberFrom(5));

        assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
