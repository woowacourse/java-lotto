package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningCalculatorTest {
    @DisplayName("WinningCalculator 가 LottoTickets 와 WinningTicket 을 이용해 당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    public void getPrizeCountTest() {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall("7");
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 7))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 8))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 8, 9))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 8, 9, 10))));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
        Assertions.assertThat(winningCalculator.getPrizeCount(3, false)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeCount(4, false)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeCount(5, false)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeCount(5, true)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeCount(6, false)).isEqualTo(1);
    }
}
