package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottosTest {
    @DisplayName("발행된 로또를 저장한다.")
    @Test
    void 발행된_로또를_저장한다() {
        Lottos lottos = new Lottos();
        lottos.add(new Lotto(List.of(1,2,3,4,5,6)));
        Assertions.assertThat(lottos.getLottos()).hasSize(1);
    }
}
