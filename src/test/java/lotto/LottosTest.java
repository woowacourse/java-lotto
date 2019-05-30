package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    @Test
    void 생성된_로또_개수_확인() {
        Lottos lottos = new Lottos(4, new AutoLottoCreator());
        assertThat(lottos.getLottos().size()).isEqualTo(4);
    }
}
