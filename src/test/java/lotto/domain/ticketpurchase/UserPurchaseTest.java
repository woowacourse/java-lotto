package lotto.domain.ticketpurchase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPurchaseTest {
    @DisplayName("구입한 티켓 수 반환 확인")
    @Test
    void Should_Return_ExactNumberOfPurchasedTickets_When_GetNumberOfAllTickets() {
        PurchasePrice purchasePrice = new PurchasePrice(2000);
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();

        UserPurchase userPurchase = new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);

        assertThat(userPurchase.getNumberOfAllTickets()).isEqualTo(2);
    }

    @DisplayName("구입 금액 객체 반환 확인")
    @Test
    void Should_Return_ExactPurchasePriceObject_When_GetPurchasePrice() {
        PurchasePrice purchasePrice = new PurchasePrice(2000);
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();

        UserPurchase userPurchase = new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);

        assertThat(userPurchase.getPurchasePrice()).isEqualTo(purchasePrice);
    }
}
