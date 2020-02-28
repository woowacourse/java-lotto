package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    @Test
    @DisplayName("LottoTicket은 다른 LottoTicket을 받아서 일치하는 숫자의 개수를 반환")
    void countMathcesWhenMachingFive() {
        Set<Integer> numbers = Arrays.stream(new int[] {1, 2, 3, 4, 5, 6})
                .boxed()
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = LottoFactory.publishLottoTicketFrom(numbers);

        Set<Integer> targetNumbers = Arrays.stream(new int[] {1, 2, 3, 4, 5, 7})
                .boxed()
                .collect(Collectors.toSet());
        LottoTicket targetLottoTicket = LottoFactory.publishLottoTicketFrom(targetNumbers);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }
}