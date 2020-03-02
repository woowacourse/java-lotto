package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.count.Count;

class RandomNumbersFactoryTest {

    @Test
    @DisplayName("Count객체를 받아 autoLottoTicktsCount의 개수만큼 Set<Integer> 생")
    void publishAutoLottoTickets() {
        Count count = new Count(10, 5);
        List<Set<Integer>> lottoTicketsNumbers = RandomNumbersFactory.publishAutoLottoTicketsNumbersFrom(count);

        assertThat(lottoTicketsNumbers.size()).isEqualTo(5);
    }
}