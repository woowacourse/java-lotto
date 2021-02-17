package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @Test
    @DisplayName("당첨 티켓 분류")
    void checkWinningTicket() {
        LottoTicket firstPrizeTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoTicket secondPrizeTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(firstPrizeTicket, secondPrizeTicket));
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        int bonusBall = 8;
        Map<PrizeType, Integer> result = lottoTickets.checkWinningTickets(winningNumbers, bonusBall);
        assertThat(result.get(PrizeType.FIRST_PRIZE)).isEqualTo(1);
        assertThat(result.get(PrizeType.SECOND_PRIZE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateProfitRate() {
        LottoTicket firstPrizeTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoTicket secondPrizeTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 8));
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(firstPrizeTicket, secondPrizeTicket));
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        int bonusBall = 8;
        Map<PrizeType, Integer> result = lottoTickets.checkWinningTickets(winningNumbers, bonusBall);
        Money money = new Money(2000);
        double profitRate = lottoTickets.calculateProfitRate(money, result);
        assertThat(profitRate).isEqualTo(1015000.0);
    }
}