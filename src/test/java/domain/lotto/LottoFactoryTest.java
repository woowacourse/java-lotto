package domain.lotto;

import static domain.money.LottoMoneyTest.*;

import org.junit.jupiter.api.Test;

import domain.money.LottoMoney;

public class LottoFactoryTest {

    @Test
    void testLottoFactoryTest() {
        LottoMoney lottoMoney = getLottoMoneyFixture();
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(lottoMoney);
    }
}
