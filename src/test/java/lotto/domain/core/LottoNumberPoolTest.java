package lotto.domain.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LottoNumberPoolTest {
    @Test
    void 셔플() {
        assertNotEquals(LottoNumberPool.random(), LottoNumberPool.random());
    }
}