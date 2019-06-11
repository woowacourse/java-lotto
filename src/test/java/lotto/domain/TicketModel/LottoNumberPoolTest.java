package lotto.domain.TicketModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LottoNumberPoolTest {
    @Test
    void 자동생성() {
        assertNotEquals(new LottoNumbers(), new LottoNumbers());
    }
}