package lotto.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ServiceConfig;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;

class PurchaseServiceTest {

    @Test
    @DisplayName("로또티켓을 구매할 수 있다.")
    public void purchaseTicketByMoney() {
        // given
        MoneyService moneyService = ServiceConfig.getMoneyService();
        Money money = new Money(2000);
        moneyService.insert(money);

        // when
        PurchaseService service = ServiceConfig.getPurchaseService();
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        List<LottoTicket> lottoTickets = service.purchase(generator, 2);

        // then
        Assertions.assertThat(lottoTickets.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("금액이 부족하면 로또티켓을 구매할 수 없다.")
    public void throwsExceptionWithNotEnoughMoney() {
        // given
        MoneyService moneyService = ServiceConfig.getMoneyService();
        Money money = new Money(2000);
        moneyService.insert(money);

        // when
        PurchaseService service = ServiceConfig.getPurchaseService();
        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> service.purchase(generator, 3));
    }
}