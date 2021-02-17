package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {
    @DisplayName("구입 금액에 따른 티켓 다발 생성")
    @Test
    void Should_ReturnPurchasedTickets_When_Purchased() {
        UserPurchase userPurchase = new UserPurchase(18000);
        LottoMachine lottoMachine = new LottoMachine();
        PurchasedLottoTickets purchasedLottoTickets = lottoMachine
            .purchaseLottoTicket(userPurchase);
        assertThat(purchasedLottoTickets.getTickets().size()).isEqualTo(18);
    }
}
