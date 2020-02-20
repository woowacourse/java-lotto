package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfitCalculatorTest {
    @DisplayName("당첨된 복권의 수와 돈을 이용하여 수익률 계산하는 테스트")
    @Test
    public void calculateProfitTest() {
        Money money = new Money("14000");
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

        Assertions.assertThat(ProfitCalculator.calculateProfit(money, winningCalculator)).isEqualTo(145111);
    }
}
