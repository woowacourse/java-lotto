package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    private Money money;
    private LottoTickets lottoTickets;
    private WinningLottoTicket winningLottoTicket;


    @BeforeEach
    private void setUp() {
        money = new Money(14000);
        AutoLottoTickets autoLottoTickets = LottoTicketsFixture.getAutoLottoTickets();
        lottoTickets = new LottoTickets(autoLottoTickets.getTickets());
        winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall(new LottoNumber(7));
    }

    @DisplayName("당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    void getPrizeCountTest() {
        LottoResult lottoResult = new LottoResult(lottoTickets, winningLottoTicket, money);

        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(1);
    }

    @DisplayName("Money 를 받아서 수익률 계산하는 테스트")
    @Test
    void calculateProfitTest() {
        LottoResult lottoResult = new LottoResult(lottoTickets, winningLottoTicket, money);

        Assertions.assertThat(lottoResult.getProfitPercent()).isEqualTo(14511107);
    }
}
