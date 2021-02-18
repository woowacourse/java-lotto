package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.Test;

class MachineTest {

    //금액입력

    @Test
    void buyTickets() {
        Machine machine = new Machine("4000");

        assertThat(machine.buyTickets(new FixedLottoGenerator()))
                .size().isEqualTo(4);
    }

    @Test
    void name() {
        //String winningNumber, List<LottoTicket> lottoTickets -> Result 반환

    }

}