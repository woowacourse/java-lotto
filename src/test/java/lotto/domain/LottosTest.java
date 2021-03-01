package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("자동 로또 생성 확인")
    @Test
    void create_auto_lotto() {
        Lottos lottos = new Lottos();

        for (int i = 0; i < 6; i++) {
            lottos.buyLotto(new LottoAutoGenerator());
        }

        assertThat(lottos.getLottos().size()).isEqualTo(6);
    }
}
