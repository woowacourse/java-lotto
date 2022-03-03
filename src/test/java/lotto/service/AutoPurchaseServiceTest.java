package lotto.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ServiceConfig;
import lotto.domain.LottoTicket;
import lotto.domain.Money;

class AutoPurchaseServiceTest {

    @Test
    @DisplayName("로또티켓을 구매할 수 있다.")
    public void purchaseTicketByMoney() {
        // given
        MoneyService moneyService = ServiceConfig.getMoneyService();
        Money money = new Money(2000);
        moneyService.insert(money);

        // when
        AutoPurchaseService service = ServiceConfig.getPurchaseService();
        List<LottoTicket> lottoTickets = service.purchaseAll();

        // then
        Assertions.assertThat(lottoTickets.size()).isEqualTo(2);
    }
}