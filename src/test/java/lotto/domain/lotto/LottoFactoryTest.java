package lotto.domain.lotto;

import lotto.domain.count.Count;
import org.junit.jupiter.api.Test;

import static lotto.domain.count.CountTest.getCountFixture;

public class LottoFactoryTest {

    @Test
    void testLottoFactoryTest() {
        Count count = getCountFixture();
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(count);
    }
}
