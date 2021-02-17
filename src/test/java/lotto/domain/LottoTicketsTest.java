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
        List<LottoTicket> lottoTicketGroup = Arrays.asList(firstPrizeTicket, secondPrizeTicket);
        LottoTickets lottoTickets = new LottoTickets(lottoTicketGroup);
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 7);
        int bonusBall = 8;
        Map<Integer, Integer> result = lottoTickets.checkWinningTickets(winningNumbers, bonusBall);
        assertThat(result.get(2000000000)).isEqualTo(1);
        assertThat(result.get(30000000)).isEqualTo(1);
    }
}