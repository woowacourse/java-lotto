package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {
    @Test
    void 금액에_맞게_생성되는지_확인() {
        Money money = new Money(2030);
        assertThat(LottoMachine.buyLottos(money).size()).isEqualTo(2);
    }
}
