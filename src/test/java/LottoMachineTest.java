import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoMachine;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    @DisplayName("로또머신은 로또 구매개수를 갖고있다")
    void 로또머신은_로또_구매개수를_갖고있다() {
        LottoMachine lottoMachine = new LottoMachine(new Money("10000"));
        assertThat(lottoMachine.getTicket()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또머신은 로또를 티켓 개수만큼 생성할 수 있다")
    void 로또머신은_로또를_티켓_개수만큼_생성할_수_있다() {
        LottoMachine lottoMachine = new LottoMachine(new Money("10000"));
        assertThat(lottoMachine.generateLottos().getLottos().size()).isEqualTo(lottoMachine.getTicket());
    }
}