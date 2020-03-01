package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import lotto.domain.count.Count;

class RandomNumbersFactoryTest {

    @Test
    void publishAutoLottoTickets() {
        Count count = new Count(10, 4);
        List<Set<Integer>> lottoTicketsNumbers = RandomNumbersFactory.publishAutoLottoTicketsNumbersFrom(count);

        assertThat(lottoTicketsNumbers.size()).isEqualTo(6);
    }
}