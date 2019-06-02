package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketingMachineTest {
    @Test
    void 주어진_숫자로_LottoTickets를_생성하는지_체크() {
        assertThat(LottoTicketingMachine.generateLottoTickets(3)).isInstanceOf(LottoTickets.class);
    }

    @Test
    void 주어진_숫자만큼의_LottoTicket을_생성하는지_체크() {
        assertThat(LottoTicketingMachine.generateLottoTickets(3).size()).isEqualTo(3);
    }
}