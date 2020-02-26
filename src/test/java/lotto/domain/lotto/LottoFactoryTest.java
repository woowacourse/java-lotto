package lotto.domain.lotto;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import lotto.domain.count.Count;
import org.junit.jupiter.api.Test;

import static lotto.domain.count.CountTest.getCountFixture;

public class LottoFactoryTest {

    @Test
    @DisplayName("LottoTicketFactory는 money를 받아 lottoTicket을 발행")
    void lottoTicketFactoryPublishsLottoTicketsBasedOnMoney() {
        LottoMoney lottoMoney = new LottoMoney(1000);
        Count count = getCountFixture();
        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(count);
    }
}
