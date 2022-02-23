package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    @DisplayName("로또 티켓을 생성한다")
    void makeLottoTicket() {
        LottoMachine lottoMachine = new LottoMachine();
        Set<LottoNumber> lottoTicket = lottoMachine.makeLottoTicket();
        assertThat(lottoTicket.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 티켓을 여러개 생성한다")
    void makeLottoTickets() {
        LottoMachine lottoMachine = new LottoMachine();
        List<Set<LottoNumber>> lottoTickets = lottoMachine.makeLottoTickets(14);
        assertThat(lottoTickets.size()).isEqualTo(14);
    }
}
