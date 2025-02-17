package model;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import view.OutputView;

class LottosTest {

    @Test
    void 로또_갯수_생성_테스트() {
        int price = 14000;
        int expectCount = 14;

        Lottos lottos = new Lottos(price);
        int result = lottos.lottoSize();

        Assertions.assertThat(result).isEqualTo(expectCount);
    }
}