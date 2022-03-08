package lotto.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.generator.RandomNumberGenerator;
import lotto.domain.generator.StringInputNumberGenerator;

class PurchasedLottoTicketsTest {

    @Test
    @DisplayName("수동과 자동 로또 티켓을 저장한다.")
    public void createPurchaseResultWithAutoAndManual() {
        // given
        LottoTicket autoTicket = LottoTicket.createLottoTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), Money.from(2000)
        );
        LottoTicket manualTicket = LottoTicket.createLottoTicket(
            new StringInputNumberGenerator("1,2,3,4,5,6"), Money.from(1000)
        );
        // when
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets(autoTicket, manualTicket);
        // then
        Assertions.assertThat(purchasedLottoTickets).isNotNull();
    }

    @Test
    @DisplayName("구매하는데 사용된 금액을 계산할 수 있다.")
    public void calculateMoneyForPurchasing() {
        // given
        LottoTicket autoTicket = LottoTicket.createLottoTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), Money.from(2000)
        );
        LottoTicket manualTicket = LottoTicket.createLottoTicket(
            new StringInputNumberGenerator("1,2,3,4,5,6"), Money.from(1000)
        );
        // when
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets(autoTicket, manualTicket);
        // then
        Assertions.assertThat(purchasedLottoTickets.sumMoney()).isEqualTo(Money.from(3000));
    }
    
    @Test
    @DisplayName("당첨번호를 받아 비교할 수 있다.")
    public void compareWithWinningTicket() {
        // given
        WinningTicket winningTicket = WinningTicket.from(
            "1,2,3,4,5,6", "7"
        );

        LottoTicket autoTicket = LottoTicket.createLottoTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), Money.from(2000)
        );
        LottoTicket manualTicket = LottoTicket.createLottoTicket(
            new StringInputNumberGenerator("1,2,3,4,5,6"), Money.from(1000)
        );

        // when
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets(autoTicket, manualTicket);
        List<LottoRank> lottoRanks = purchasedLottoTickets.compareWinningTicket(winningTicket);

        // then
        Assertions.assertThat(lottoRanks).contains(LottoRank.FIRST);
    }
}