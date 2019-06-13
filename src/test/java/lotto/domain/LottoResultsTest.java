package lotto.domain;

import lotto.domain.factory.LottoResultsFactory;
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
        List<LottoTicket> lottoTickets =
                Arrays.asList(new LottoTicket(lottoNumbersFirst), new LottoTicket(lottoNumbersSecond));


        WinningLotto winningLotto = new WinningLotto(new LottoTicket(Arrays.asList(1, 2, 3, 6, 7, 8)), 10);

        LottoResults lottoResults =
                LottoResultsFactory.create(
                        new LottoTickets(lottoTickets), winningLotto, new LottoMoney(2000));

        assertThat(lottoResults.getYield()).isEqualTo(500);
    }
}