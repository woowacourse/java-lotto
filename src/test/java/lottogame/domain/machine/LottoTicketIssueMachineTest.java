package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lottogame.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketIssueMachineTest {

    @Test
    @DisplayName("입력된 금액이 티켓 최소 구입 금액보다 적을시 예외처리")
    void testMinPurchaseAmountException() {
        assertThatThrownBy(() -> new LottoTicketIssueMachine(new Money(0), 0))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoTicketIssueMachine(new Money(999), 5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액에 해당하는 만큼 자동 로또 티켓 발급")
    void testIssueAutoTicketsByInputMoney() {
        assertThat(new LottoTicketIssueMachine(new Money(1_000), 0)
            .issueAutoTickets().getLottoTickets()).hasSize(1);
        assertThat(new LottoTicketIssueMachine(new Money(1_999), 0)
            .issueAutoTickets().getLottoTickets()).hasSize(1);
        assertThat(new LottoTicketIssueMachine(new Money(2_000), 0)
            .issueAutoTickets().getLottoTickets()).hasSize(2);
    }

    @Test
    @DisplayName("입력한 개수만큼 수동 로또 티켓 발급")
    void testIssueManualTicketsByInputCount() {
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(
            new Money(10_000), 5);
        List<Set<Integer>> manualNumbers = initManualNumbers();
        assertThat(lottoTicketIssueMachine.issueManualTickets(manualNumbers).getLottoTickets())
            .hasSize(3);
    }

    @Test
    @DisplayName("입력한 개수가 총 구입 금액을 넘어설 때 예외처리")
    void testInputCountIsNotIntegerException() {
        assertThatThrownBy(() -> new LottoTicketIssueMachine(new Money(4_999), 5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private List<Set<Integer>> initManualNumbers() {
        List<Set<Integer>> manualNumbers = new ArrayList<>();
        manualNumbers.add(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        manualNumbers.add(new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 7)));
        manualNumbers.add(new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 8)));
        return manualNumbers;
    }
}
