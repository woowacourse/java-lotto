package lotto.domain.ticket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LottoNumberPoolTest {
    @Test
    void 자동생성() {
        assertNotEquals(new LottoNumbers(), new LottoNumbers());
    }

    @Test
    void 셔플() {
        assertNotEquals(LottoNumberPool.random(), LottoNumberPool.random());
    }
}