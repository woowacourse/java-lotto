package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @DisplayName("발행된 로또를 저장한다.")
    @Test
    void makeLotto() {
        Lottos lottos = new Lottos();

        lottos.add(new Lotto(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        )));

        assertThat(lottos.getLottos()).hasSize(1);
    }

}
