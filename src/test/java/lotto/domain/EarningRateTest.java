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
        String winningBallInput = "1,2,3,4,5,6";
        LottoBall bonusBall = new LottoBall("7");
        LottoTicket winningTicketInput = new LottoTicket(winningBallInput);

        String input1 = "1,2,3,4,5,6";
        String input2 = "1,2,3,4,5,6";
        String input3 = "1,2,3,4,5,6";

        LottoTickets.insertLottoTicket(new LottoTicket(input1));
        LottoTickets.insertLottoTicket(new LottoTicket(input2));
        LottoTickets.insertLottoTicket(new LottoTicket(input3));

        WinningTicket winningTicket = new WinningTicket(winningTicketInput.getLottoTicket(), bonusBall);
        Map<Rank,Long> eachRankCount = Rank.calculateEachRankCount(winningTicket);

        EarningRate earningRate = new EarningRate();

        Assertions.assertThat(earningRate.calculateEarningRate(eachRankCount)).isEqualTo(60000000);
    }
}