package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoResultTest {
    private Money money;
    private LottoTickets lottoTickets;
    private WinningLottoTicket winningLottoTicket;
    private LottoResult lottoResult;

    @BeforeEach
    private void setUp() {
        money = new Money(14000);
        AutoLottoTickets autoLottoTickets = LottoTicketsFixture.getAutoLottoTickets();
        lottoTickets = new LottoTickets(autoLottoTickets.getTickets());
        LottoTicket lottoTicket = new LottoTicket(new ArrayList(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        ));
        winningLottoTicket = new WinningLottoTicket(lottoTicket, LottoNumbers.getLottoNumber(7));
        lottoResult = new LottoResult(lottoTickets, winningLottoTicket, money);
    }

    @DisplayName("당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    void getPrizeCountTest() {
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(1);
    }

    @DisplayName("Money 를 받아서 수익률 계산하는 테스트")
    @Test
    void calculateProfitTest() {
        Assertions.assertThat(lottoResult.getProfitPercent()).isEqualTo(14511107);
    }
}
