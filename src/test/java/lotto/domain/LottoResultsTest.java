package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultsTest {
    @Test
    void 수익률_확인() {
        List<Integer> lottoNumbersFirst = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );

        List<Integer> lottoNumbersSecond = Arrays.asList(
                1, 2, 3, 4, 5, 7
        );
        LottoTickets lottoTickets = new LottoTickets(
                Arrays.asList(new UserLottoTicket(lottoNumbersFirst), new UserLottoTicket(lottoNumbersSecond))
        );

        UserLottoTicket rewardTicket = new UserLottoTicket(
                Arrays.asList(1, 2, 3, 6, 7, 8)
        );

        LottoResults lottoResults = new LottoResults(lottoTickets, rewardTicket);

        assertThat(lottoResults.getYield()).isEqualTo(500);
    }
}