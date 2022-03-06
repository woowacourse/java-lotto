package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static domain.LottoTest.createLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    @DisplayName("로또를 자동으로 발행한다")
    @Test
    void create_auto_lottos() {
        int autoLottosSize = new LottoMachine()
                .createAutoLottos(3)
                .getLottos()
                .size();
        assertThat(autoLottosSize).isEqualTo(3);
    }

    @DisplayName("로또를 수동으로 발행한다")
    @Test
    void create_manual_lottos() {
        int manualLottosSize = new LottoMachine()
                .createManualLottos(Arrays.asList(new Lotto(createLottoNumbers(6, 5, 4, 3, 2, 1)),
                        new Lotto(createLottoNumbers(11, 5, 4, 3, 2, 1))))
                .getLottos()
                .size();
        assertThat(manualLottosSize).isEqualTo(2);
    }
}
