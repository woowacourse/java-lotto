package lottogame.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import lottogame.domain.Count;
import lottogame.domain.Money;
import lottogame.domain.machine.LottoTicketIssueMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    @DisplayName("LottoTickets 생성 테스트")
    void testCreate() {
        LottoTicketIssueMachine lottoTicketIssueMachine =
            new LottoTicketIssueMachine(new Money(3000), new Count(0));
        LottoTickets lottoTickets = lottoTicketIssueMachine.issueAutoTickets();
        assertThat(lottoTickets.getLottoTickets()).hasSize(3);
    }

    @Test
    @DisplayName("2개의 LottoTickets 합치기 테스트")
    void testJoinTwoLottoTickets() {
        LottoTicketIssueMachine lottoTicketIssueMachine =
            new LottoTicketIssueMachine(new Money(2000), new Count(1));
        LottoTickets manualTickets = lottoTicketIssueMachine.issueManualTickets(
            Collections.singletonList(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)))
        );
        LottoTickets autoTickets = lottoTicketIssueMachine.issueAutoTickets();
        assertThat(manualTickets.getLottoTickets()).hasSize(1);
        assertThat(autoTickets.getLottoTickets()).hasSize(1);
        assertThat(manualTickets.joinLottoTickets(autoTickets).getLottoTickets()).hasSize(2);
    }
}
