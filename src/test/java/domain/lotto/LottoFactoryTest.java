package domain.lotto;

import domain.money.LottoMoney;
import org.junit.jupiter.api.Test;

import static domain.money.LottoMoneyTest.getLottoMoneyFixture;

public class LottoFactoryTest {

    @Test
    void testLottoFactoryTest() {
        LottoMoney lottoMoney = getLottoMoneyFixture();
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(lottoMoney);
    }
}
