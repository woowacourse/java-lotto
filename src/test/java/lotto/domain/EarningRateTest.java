package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {
    @Test
    @DisplayName("수익률 테스트")
    void calculate_total_money_test() {
        LottoBall bonusBall = new LottoBall("7");
        LottoTicket winningTicketInput = new LottoTicket("1,2,3,4,5,6");
        LottoTickets lottoTickets = new LottoTickets();

        lottoTickets.insertLottoTicket(new LottoTicket("1,2,3,4,5,6"));
        lottoTickets.insertLottoTicket(new LottoTicket("1,2,3,4,5,6"));
        lottoTickets.insertLottoTicket(new LottoTicket("1,2,3,4,5,6"));

        WinningTicket winningTicket = new WinningTicket(winningTicketInput, bonusBall);
        Map<Rank,Long> eachRankCount = Rank.calculateEachRankCount(winningTicket,lottoTickets);

        Assertions.assertThat(EarningRate.calculateEarningRate(eachRankCount, new Money("3000")))
                .isEqualTo(200_000_000);
    }
}