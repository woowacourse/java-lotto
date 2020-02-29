package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.sun.tools.javac.util.List;
import lotto.domain.count.Count;

class LottoFactoryTest {

    @Test
    void publishLottoTicketsFrom() {
        Set<Integer> lottoNumbers = Arrays.stream(new int[] {1, 2, 3, 4, 5, 6})
                .boxed()
                .collect(Collectors.toSet());
        LottoTickets lottoTickets = LottoFactory.publishLottoTicketsFrom(List.of(lottoNumbers));

        assertThat(lottoTickets.getLottoTickets()).isEqualTo(List.of("[1, 2, 3, 4, 5, 6]"));
    }

    @Test
    void publishAutoLottoTickets() {
        Count count = new Count(10, 4);
        LottoTickets lottoTickets = LottoFactory.publishAutoLottoTicketsFrom(count);

        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(6);
    }

    @Test
    void publicWinningLotto() {
        Set<Integer> lottoNumbers = Arrays.stream(new int[] {1, 2, 3, 4, 5, 6})
                .boxed()
                .collect(Collectors.toSet());
        Integer bonusNumber = 7;

        assertThat(LottoFactory.publishWinningLotto(lottoNumbers, bonusNumber)).isNotNull();
    }
}