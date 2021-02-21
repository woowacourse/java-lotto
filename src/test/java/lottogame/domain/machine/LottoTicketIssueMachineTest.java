package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import lottogame.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketIssueMachineTest {

    @Test
    @DisplayName("구입 금액에 해당하는 만큼 로또 티켓 발급")
    void lottoTicketMachineInsertMoney() {
        assertThat(LottoTicketIssueMachine.issueTickets(new Money(999))
            .getLottoTickets().size()).isEqualTo(0);
        assertThat(LottoTicketIssueMachine.issueTickets(new Money(1000))
            .getLottoTickets().size()).isEqualTo(1);
        assertThat(LottoTicketIssueMachine.issueTickets(new Money(3000))
            .getLottoTickets().size()).isEqualTo(3);
    }
}
