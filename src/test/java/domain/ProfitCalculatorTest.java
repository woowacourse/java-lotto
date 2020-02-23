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
        Money money = new Money(14000);
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall(new LottoNumber(7));
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(8)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(8),
                new LottoNumber(9)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);

        Assertions.assertThat(ProfitCalculator.calculateProfit(money, winningCalculator)).isEqualTo(14511107);
    }
}
