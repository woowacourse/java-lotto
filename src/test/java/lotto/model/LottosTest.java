package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("3개의 로또 생성하는지 확인")
    void GenerateLottos() {
        Lottos lottos = new Lottos(new LottoNumberGenerator(), 3);

        assertThat(lottos.getLottos().size()).isEqualTo(3);
    }
}
