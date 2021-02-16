package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoLottoMachineTest {
    @DisplayName("목표 장수에 맞추어 티켓을 생성하는지 확인")
    @Test
    void 목표에_맞추어_티켓을_생성한다(){
        AutoLottoMachine autoLottoMachine = new AutoLottoMachine();
        int numberOfTickets = 5;

        List<LottoTicket> tickets = autoLottoMachine.createTickets(5);

        assertThat(tickets.size()).isEqualTo(numberOfTickets);
    }
}