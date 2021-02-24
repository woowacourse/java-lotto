package lotto.lottoticket;

import lotto.lottogame.LottoCount;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.money.Money;
import lotto.ranking.Ranking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTicketsTest {
    @Test
    @DisplayName("티켓들 생성 확인")
    void ticketsCreate() {
        LottoTickets lottoTickets = new LottoTickets(LottoCount.valueOf(new Money("20000")), new RandomNumbersGenerator());
        assertThat(lottoTickets.getTickets().size()).isEqualTo(20);
    }

    @Test
    @DisplayName("티켓들 결과 생성 확인")
    void checkTicketsRanking() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        BonusBall bonusBall = new BonusBall("7", winnerTicket);
        LottoTickets lottoTickets = new LottoTickets(LottoCount.valueOf(new Money("2000000")), new RandomNumbersGenerator());
        List<Ranking> result = lottoTickets.makeResult(winnerTicket, bonusBall);
        assertThat(result.size()).isEqualTo(2000);
    }
}
