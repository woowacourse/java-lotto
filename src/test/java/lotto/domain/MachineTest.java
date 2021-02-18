package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.Test;

class MachineTest {

    @Test
    void buyTickets() {
        assertThat(new Machine("4000").getTickets())
            .size().isEqualTo(4);
    }

    @Test
    void name() {
        //String winningNumber, List<LottoTicket> lottoTickets -> Result 반환

    }

}