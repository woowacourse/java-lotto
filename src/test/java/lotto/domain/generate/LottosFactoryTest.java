package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Price;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottosFactoryTest {

    @Test
    void 수동_횟수_음수() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottosFactory(new Price(10000), -1);
        });
    }

    @Test
    void 수동_횟수_초과() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottosFactory(new Price(10000), 11);
        });
    }

    @Test
    void 생성확인() {
        Price price = new Price(1000);
        LottosFactory lottosFactory = new LottosFactory(price, 1);
        assertThat(lottosFactory.generateLottos(Arrays.asList("1,2,3,4,5,6"), price)).isEqualTo(new Lottos(Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)))));
    }
}
