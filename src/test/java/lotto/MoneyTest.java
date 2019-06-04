package lotto;

import lotto.domain.money.Money;
import lotto.domain.money.exception.IllegalMoneyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MoneyTest {
    @Test
    void 머니_생성_테스트() {
        assertDoesNotThrow(() -> Money.create(1000));
    }

    @Test
    void 음수_액수() {
        assertThrows(IllegalMoneyException.class, () -> Money.create(-500));
    }

    @Test
    void 천원_단위가_아닌_액수() {
        assertThrows(IllegalMoneyException.class, () -> Money.create(999));
    }
}
