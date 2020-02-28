package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitCalculatorTest {
    @DisplayName("당첨된 복권의 수와 돈을 이용하여 수익률 계산하는 테스트")
    @Test
    public void calculateProfitTest() {
        Money money = new Money(14000);
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall(new LottoNumber(7));
        LottoTickets lottoTickets = LottoTicketsFixture.getLottoTickets();
        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);

        Assertions.assertThat(ProfitCalculator.calculateProfit(money, winningCalculator)).isEqualTo(14511107);
    }
}
