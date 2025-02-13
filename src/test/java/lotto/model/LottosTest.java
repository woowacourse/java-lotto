package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("발행된 로또를 저장한다.")
    @Test
    void makeLotto() {
        Lottos lottos = new Lottos();

        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        assertThat(lottos.getLottos()).hasSize(1);
    }

}
