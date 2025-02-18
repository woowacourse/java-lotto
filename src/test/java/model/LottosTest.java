package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("로또 개수만큼 생성")
    void createLottoUsingCount() {
        int count = 14;
        Lottos lottos = new Lottos(count);

        Assertions.assertThat(lottos.getLottos()).hasSize(count);
    }
}
