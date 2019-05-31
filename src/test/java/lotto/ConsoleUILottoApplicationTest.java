package lotto;

import lotto.domain.Price;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleUILottoApplicationTest {

    @Test
    void 수동_횟수_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleUILottoApplication.getSelfCount(new Price(10000), -1);
        });
    }

    @Test
    void 수동_횟수_초과() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleUILottoApplication.getSelfCount(new Price(10000), 11);
        });
    }
}
