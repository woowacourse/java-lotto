import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    @DisplayName("로또머신은 로또 구매개수를 갖고있다")
    void 로또머신은_로또_구매개수를_갖고있다() {
        LottoMachine lottoMachine = new LottoMachine(new Price("10000"));
        assertThat(lottoMachine.getTicket()).isEqualTo(10);
    }

}