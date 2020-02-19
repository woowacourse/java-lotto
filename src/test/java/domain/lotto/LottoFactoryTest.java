package domain.lotto;

import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @Test
    void testLottoFactoryTest() {
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(10);
    }
}
