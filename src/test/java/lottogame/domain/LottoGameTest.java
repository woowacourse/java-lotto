package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private List<Set<Integer>> manualTicketNumbers;

    @BeforeEach
    void setUp() {
        this.manualTicketNumbers = new ArrayList<>();
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(19, 18, 17, 16, 15, 14)));
    }

    @Test
    @DisplayName("총 구입 금액과 발부한 티켓 개수가 일치하는지 확인")
    void testSameMatchCount() {
        Money money = new Money(10_000);
        Count count = new Count(4);
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(money, count);
        LottoGame lottoGame = new LottoGame(lottoTicketIssueMachine);

        assertThat(lottoGame.issueManualTickets(this.manualTicketNumbers).getLottoTickets()).hasSize(4);
        assertThat(lottoGame.issueAutoTickets().getLottoTickets()).hasSize(6);
    }

    @Test
    @DisplayName("총 수익률이 일치하는지 확인")
    void testSameBonusMatch() {
        Money money = new Money(4_000);
        Count count = new Count(4);
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(money, count);
        LottoGame lottoGame = new LottoGame(lottoTicketIssueMachine);

        LottoTickets lottoTickets = lottoGame.issueManualTickets(this.manualTicketNumbers);
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(new HashSet<>(Arrays.asList(1,2,3,8,9,10)), 19);

        assertThat(lottoGame.getYield(lottoTickets, lottoWinningNumbers)).isEqualTo(3.75);
    }
}