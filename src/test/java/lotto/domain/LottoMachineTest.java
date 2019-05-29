package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoMachineTest {
    @Test
    void 금액에_맞게_생성되는지_확인() {
        assertThat(LottoMachine.buyLottos(2030).size()).isEqualTo(2);
    }

    @Test
    void 돈이_부족한_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoMachine.buyLottos(500));
    }

    @Test
    void 최대_구매_가능_금액을_넘어간_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoMachine.buyLottos(1_000_000_001));
    }
}
