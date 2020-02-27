package lotto.domain.lotto;

import lotto.domain.money.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketFactoryTest {

    @Test
    @DisplayName("LottoTicketFactory는 money를 받아 lottoTicket을 발행")
    void lottoTicketFactoryPublishsLottoTicketsBasedOnMoney() {
        LottoMoney lottoMoney = new LottoMoney(1000);
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(lottoMoney);
    }
}
