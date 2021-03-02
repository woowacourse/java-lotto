package lotto.domain.lottomachine;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoLottoMachineTest {
    private final AutoLottoMachine autoLottoMachine = new AutoLottoMachine();

    @DisplayName("목표 장수에 맞추어 티켓을 생성하는지 확인")
    @Test
    void 목표에_맞추어_티켓을_생성한다() {
        int numberOfTickets = 5;

        LottoTickets tickets = autoLottoMachine.createTickets(5);

        assertThat(tickets.getLottoTickets().size()).isEqualTo(numberOfTickets);
    }
}