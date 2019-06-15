package lotto.domain;

import lotto.domain.factory.LottoTicketFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinStatisticsTest {
    @Test
    void 수익률_계산하기() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                LottoTicketFactory.getInstance().create("1,2,3,4,5,7"), LottoTicketFactory.getInstance().create("1,3,4,7,10,11"),
                LottoTicketFactory.getInstance().create("1,3,4,20,21,22"), LottoTicketFactory.getInstance().create("1,2,5,7,16,22"));
        WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 22);

        assertThat(new WinStatistics(lottoTickets, winningLotto).calculateProfitRate(4000)).isEqualTo(37875);
    }
}
