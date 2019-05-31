package lotto;

import lotto.domain.money.IllegalMoneyException;
import lotto.domain.money.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MoneyTest {
    @Test
    void 머니_생성_테스트() {
        assertDoesNotThrow(() -> new Money("1000"));
    }

    @Test
    void 천원단위_아닐때() {
        assertThrows(IllegalMoneyException.class, () -> new Money("-999"));
    }
}
