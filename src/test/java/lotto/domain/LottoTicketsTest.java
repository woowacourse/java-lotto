package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;


class LottoTicketsTest {
    @Test
    void 당첨_결과_확인() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );

        List<Integer> lottoNumbersSecond = Arrays.asList(
                1, 2, 3, 4, 5, 7
        );
        LottoTickets lottoTickets =
                new LottoTickets(Arrays.asList(new LottoTicket(lottoNumbersFirst), new LottoTicket(lottoNumbersSecond)));
        WinningLotto winningLotto = new WinningLotto(new LottoTicket(Arrays.asList(1, 2, 3, 6, 7, 8)), 10);

        Map<LottoRank, Integer> results = new TreeMap<>();
        results.put(LottoRank.FOURTH, 2);

        assertThat(lottoTickets.getRewards(winningLotto)).isEqualTo(results);
    }
}