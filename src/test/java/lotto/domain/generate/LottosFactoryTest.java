package lotto.domain.generate;

import lotto.domain.Price;
import org.junit.jupiter.api.Test;

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
}
