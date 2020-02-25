package lotto.domain.lotto;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.Test;

import static lotto.domain.money.LottoMoneyTest.getLottoMoneyFixture;

public class LottoTicketFactoryTest {

    @Test
    void testLottoFactoryTest() {
        LottoMoney lottoMoney = getLottoMoneyFixture();
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(lottoMoney);
    }
}
