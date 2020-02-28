package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningCalculatorTest {
    private WinningLottoTicket winningLottoTicket;

    @BeforeEach
    private void setUp() {
        winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall(new LottoNumber(7));
    }

    @DisplayName("WinningCalculator 가 LottoTickets 와 WinningTicket 을 이용해 당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    void getPrizeCountTest() {
        LottoTickets lottoTickets = LottoTicketsFixture.getLottoTickets();

        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(1);
    }
}
