package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 구입_금액에_해당하는_개수의_로또를_발행한다() {
        Lottos lottos = new Lottos(2000);

        assertThat(lottos.getLottos().size()).isEqualTo(2);
    }

}