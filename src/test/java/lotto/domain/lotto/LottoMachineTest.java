package lotto.domain.lotto;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    void buyTickets() {
        LottoMachine lottoMachine = new LottoMachine(Money.valueOf("1000"));
        List<LottoTicket> lottoTickets = lottoMachine.buyTickets(new FixedLottoGenerator());

        assertThat(lottoTickets).containsExactly(new LottoTicket("1,2,3,4,5,6"));
    }
}