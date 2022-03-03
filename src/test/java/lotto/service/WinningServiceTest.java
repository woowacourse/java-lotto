package lotto.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.config.ServiceConfig;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoTicket;
import lotto.domain.Money;
import lotto.domain.WinningTicket;
import lotto.domain.generator.NumberGenerator;
import lotto.domain.generator.RandomNumberGenerator;

class WinningServiceTest {

    @Test
    @DisplayName("구매한 로또티켓을 당첨번호와 비교한다.")
    public void compareWinningTicketWithLottoTicket() {
        // given
        MoneyService moneyService = ServiceConfig.getMoneyService();
        int amount = 8000;
        moneyService.insert(new Money(amount));

        NumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX);
        PurchaseService purchaseService = ServiceConfig.getPurchaseService();
        purchaseService.purchase(generator, amount / LottoTicket.PRICE);

        WinningService winningService = ServiceConfig.getWinningService();
        WinningTicket winningTicket = new WinningTicket(new LottoTicket(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6)
            )), LottoNumber.from(7));
        // when
        List<LottoRank> compareResult = winningService.compare(winningTicket);
        // then
        Assertions.assertThat(compareResult.size()).isEqualTo(8);
    }

}