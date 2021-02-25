package lotto.lottoticket;

import lotto.lottogame.LottoCount;
import lotto.lottoticket.ticketnumber.RandomNumbersGenerator;
import lotto.ranking.Ranking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTicketsTest {
    @Test
    @DisplayName("티켓들 생성 확인")
    void ticketsCreate() {
        LottoTickets lottoTickets = new LottoTickets(new LottoCount(20), new RandomNumbersGenerator());
        assertThat(lottoTickets.getTickets().size()).isEqualTo(20);
    }

    @Test
    @DisplayName("티켓들 결과 생성 확인")
    void checkTicketsRanking() {
        WinnerTicket winnerTicket = new WinnerTicket("1, 2, 3, 4, 5, 6");
        BonusBall bonusBall = new BonusBall("7", winnerTicket);
        LottoTickets lottoTickets = new LottoTickets(new LottoCount(2000), new RandomNumbersGenerator());
        List<Ranking> result = lottoTickets.makeResult(winnerTicket, bonusBall);
        assertThat(result.size()).isEqualTo(2000);
    }

    @Test
    @DisplayName("티켓 추가 확인")
    void addTickets() {
        LottoTickets lottoTickets = new LottoTickets(new LottoCount(2), new RandomNumbersGenerator());
        lottoTickets.addTickets(new LottoCount(2), new RandomNumbersGenerator());
        assertThat(lottoTickets.getTickets().size()).isEqualTo(4);
    }
}
