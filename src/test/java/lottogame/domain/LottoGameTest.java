package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

    private LottoGame lottoGame;
    private List<Set<Integer>> manualTicketNumbers;

    @BeforeEach
    void setUp() {
        Money money = new Money(10_000);
        Count count = new Count(4);
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(money, count);
        this.lottoGame = new LottoGame(lottoTicketIssueMachine);
        this.manualTicketNumbers = new ArrayList<>();
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 7)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(8, 9, 10, 11, 12, 13)));
        this.manualTicketNumbers.add(new HashSet<>(Arrays.asList(19, 18, 17, 16, 15, 14)));
    }

    @Test
    @DisplayName("총 구입 금액과 발부한 티켓 개수가 일치하는지 확인")
    void testSameMatchCount() {
        assertThat(this.lottoGame.issueManualTickets(this.manualTicketNumbers).getLottoTickets())
            .hasSize(4);
        assertThat(this.lottoGame.issueAutoTickets().getLottoTickets()).hasSize(6);
    }

    @Test
    @DisplayName("총 수익률이 일치하는지 확인")
    void testSameBonusMatch() {
        LottoTickets manualTickets = this.lottoGame.issueManualTickets(this.manualTicketNumbers);
        LottoTickets autoTickets = this.lottoGame.issueAutoTickets();
        LottoTickets lottoTickets = manualTickets.joinLottoTickets(autoTickets);
        assertThat(this.lottoGame.getYield(getLottoGameResult(lottoTickets))).isEqualTo(5);
    }

    private Map<Rank, Integer> getLottoGameResult(final LottoTickets lottoTickets) {
        return this.lottoGame.getMatchingResult(
            lottoTickets, new LottoWinningNumbers(new HashSet<>(Arrays.asList(1, 2, 3, 8, 9, 10)), 45)
        );
    }
}