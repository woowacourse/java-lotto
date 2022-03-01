package lotto.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ServiceConfig;
import lotto.domain.LottoTicket;
import lotto.domain.Money;

class PurchaseServiceTest {

    @Test
    @DisplayName("금액으로 로또티켓을 구매할 수 있다.")
    public void purchaseTicketByMoney() {
        // given
        PurchaseService service = ServiceConfig.getPurchaseService();
        Money money = new Money(2000);
        // when
        List<LottoTicket> lottoTickets = service.purchaseAndPersist(money);
        // then
        Assertions.assertThat(lottoTickets.size()).isEqualTo(2);
    }
}