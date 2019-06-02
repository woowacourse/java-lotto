package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoMoneyTest {
    @Test
    void 금액_음수_입력() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoMoney(-1);
        });
    }

    @Test
    void 천원_단위_아닌_입력() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoMoney(1330);
        });
    }
}