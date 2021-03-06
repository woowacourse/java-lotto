package lotto.lottogame;

import lotto.lottoticket.BonusBall;
import lotto.lottoticket.LottoNumber;
import lotto.lottoticket.LottoTickets;
import lotto.lottoticket.WinnerTicket;
import lotto.money.Money;
import lotto.ranking.Ranking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoGameResultTest {
    private LottoGame lottoGame;

    @BeforeEach
    void setUp() {
        lottoGame = new LottoGame(new LottoTickets(new LottoCount(1), () ->
                Arrays.asList(LottoNumber.of(1),
                        LottoNumber.of(2),
                        LottoNumber.of(3),
                        LottoNumber.of(4),
                        LottoNumber.of(5),
                        LottoNumber.of(6))));
    }

    @Test
    @DisplayName("상금 계산 확인")
    void calculateResultTest() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        lottoGame.calculateStatistics(winnerTicket, new BonusBall("7", winnerTicket));
        assertEquals(lottoGame.createResult(new Money("2000000000")).getProfit(), 1);
    }

    @Test
    @DisplayName("3등 통계 확인")
    void calculateThirdResultTest() {
        WinnerTicket winnerTicket = new WinnerTicket("1,2,3,4,5,40");
        BonusBall bonusBall = new BonusBall("7", winnerTicket);
        lottoGame.calculateStatistics(winnerTicket, bonusBall);
        assertEquals(lottoGame.createResult(new Money("3000")).getStatistics().findRankingCount(Ranking.THIRD), 1);
    }
}