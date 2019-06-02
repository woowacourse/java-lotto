package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinStatisticsTest {
    @Test
    void 맵확인() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                LottoTicketFactory.create("1,2,3,4,5,6"), LottoTicketFactory.create("7,8,9,10,11,12"), LottoTicketFactory.create("1,2,4,10,11,12"));
        WinningLotto winningLotto = WinningLotto.of("1,2,3,40,41,42", 36);

        WinStatistics winStatistics = new WinStatistics(lottoTickets, winningLotto);
        System.out.println(winStatistics.toString());
    }

    @Test
    void 수익률_계산하기() {
        List<LottoTicket> lottoTickets = Arrays.asList(
                LottoTicketFactory.create("1,2,3,4,5,6"), LottoTicketFactory.create("7,8,9,10,11,12"), LottoTicketFactory.create("1,2,4,10,11,12"));
        WinningLotto winningLotto = WinningLotto.of("1,2,3,40,41,42", 36);

        assertThat(new WinStatistics(lottoTickets, winningLotto).calculateProfitRate(3000)).isEqualTo(60);
    }
}
