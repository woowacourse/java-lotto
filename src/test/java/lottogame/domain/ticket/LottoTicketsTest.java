package lottogame.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;

import lottogame.domain.Money;
import lottogame.domain.machine.LottoTicketIssueMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    @DisplayName("LottoTickets 생성 테스트")
    void testCreate() {
        LottoTicketIssueMachine lottoTicketIssueMachine =
            new LottoTicketIssueMachine(new Money(3000));
        LottoTickets lottoTickets = lottoTicketIssueMachine.issueTickets();
        assertThat(lottoTickets.getLottoTickets()).hasSize(3);
    }
}
