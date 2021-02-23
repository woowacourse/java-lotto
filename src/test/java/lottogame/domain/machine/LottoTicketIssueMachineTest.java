package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lottogame.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketIssueMachineTest {

    @Test
    @DisplayName("구입 금액에 해당하는 만큼 로또 티켓 발급")
    void testInsertMoney() {
        assertThat(new LottoTicketIssueMachine(new Money(1000)).issueTickets()
            .getLottoTickets()).hasSize(1);
        assertThat(new LottoTicketIssueMachine(new Money(1999)).issueTickets()
            .getLottoTickets()).hasSize(1);
        assertThat(new LottoTicketIssueMachine(new Money(2000)).issueTickets()
            .getLottoTickets()).hasSize(2);
    }

    @Test
    @DisplayName("입력된 금액이 최소 구입 금액보다 적을시 예외처리")
    void testMinPurchaseAmountException() {
        assertThatThrownBy(()->new LottoTicketIssueMachine(new Money(0)))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->new LottoTicketIssueMachine(new Money(999)))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
