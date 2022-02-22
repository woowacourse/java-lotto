package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {

    @Test
    @DisplayName("로또 티켓을 생성한다")
    void makeLottoTicket() {
        LottoMachine lottoMachine = new LottoMachine();
        Set<LottoNumber> lottoTicket = lottoMachine.makeLottoTicket();
        assertThat(lottoTicket.size()).isEqualTo(6);
    }
}
