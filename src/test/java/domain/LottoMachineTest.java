package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    @DisplayName("로또를 자동으로 발행한다")
    @Test
    void createLotto() {
        assertThat(new LottoMachine(new Payment(2000))
                .createAutoLottos())
                .isInstanceOf(Lottos.class);
    }
}
