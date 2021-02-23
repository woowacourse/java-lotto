package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("수동, 자동 로또 생성 확인")
    @Test
    void createLottos() {
        Lottos lottos = new Lottos(
                ManualLotto.createManualLotto(Arrays.asList("1, 2, 3, 20, 21, 40", "1, 2, 20, 25, 29, 45")),
                AutoLotto.createAutoLotto(0)
        );

        assertThat(lottos).isEqualTo(new Lottos(
                ManualLotto.createManualLotto(Arrays.asList("1, 2, 3, 20, 21, 40", "1, 2, 20, 25, 29, 45")),
                AutoLotto.createAutoLotto(0)
        ));
    }


}
