import domain.LottoRule;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoMachine;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    private final LottoMachine lottoMachine = new LottoMachine();

    @Test
    @DisplayName("로또머신은 로또를 입력받은 돈에 알맞게 생성할 수 있다")
    void 로또머신은_로또를_티켓_개수만큼_생성할_수_있다() {
        // given
        Money money = new Money("10000");
        int ticket = money.getAmount() / LottoRule.LOTTO_PRICE.getValue();

        // when
        int lottoCount = lottoMachine.buyLottos(money).getLottoCount();

        // then
        assertThat(lottoCount)
                .isEqualTo(ticket);
    }
}
