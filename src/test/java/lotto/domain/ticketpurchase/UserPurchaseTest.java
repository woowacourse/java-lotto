package lotto.domain.ticketpurchase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


public class UserPurchaseTest {
    @DisplayName("구입 금액보다 수동 티켓 총 금액이 더 많을 시 에러")
    @Test
    void Should_ThrowException_When_ManualTicketPriceGreaterThanPurchasePrice() {
        PurchasePrice purchasePrice = new PurchasePrice(20000);
        Assertions.assertThatThrownBy(() ->
                new UserPurchase(purchasePrice, 21)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위일 때 수동 로또와 자동 로또 개수 확인")
    @Test
    void Should_ReturnNumberOfTickets_When_ExactlyDividedByThousand() {
        PurchasePrice purchasePrice = new PurchasePrice(20000);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, 3);
        assertThat(userPurchase.getManualTicketCount()).isEqualTo(3);
        assertThat(userPurchase.getAutoTicketCount()).isEqualTo(17);
    }
}
