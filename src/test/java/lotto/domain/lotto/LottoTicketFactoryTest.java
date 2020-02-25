package lotto.domain.lotto;

import lotto.domain.count.Count;
import org.junit.jupiter.api.Test;

import static lotto.domain.count.CountTest.getCountFixture;

public class LottoTicketFactoryTest {

    @Test
    void testLottoFactoryTest() {
        Count count = getCountFixture();
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(count);
    }
}
