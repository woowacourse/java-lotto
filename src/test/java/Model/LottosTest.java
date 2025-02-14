package Model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 로또_갯수_생성_테스트() {
        int count = 14;
        int price = 14000;
        Lottos lottos = new Lottos(price);

        Assertions.assertThat(lottos.getLottos().size()).isEqualTo(count);
    }

}