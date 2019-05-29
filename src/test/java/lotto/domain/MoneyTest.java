package lotto.domain;

import lotto.domain.domainexception.InvalidMoneyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 최소_금액보다_작은_금액_테스트() {
        assertThrows(InvalidMoneyException.class, () -> new Money(999));
    }
    @Test
    void 천원_단위가_아닐_때_테스트() {
        assertThrows(InvalidMoneyException.class, () -> new Money(1001));
    }
}
