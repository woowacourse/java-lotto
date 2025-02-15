package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_개수만큼_생성() {
        int count = 14;
        Lottos lottos = new Lottos(count);

        Assertions.assertThat(lottos.getLottos().size()).isEqualTo(count);
    }

}