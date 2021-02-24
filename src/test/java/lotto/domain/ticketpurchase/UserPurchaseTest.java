package lotto.domain.ticketpurchase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


public class UserPurchaseTest {

    @DisplayName("구입 금액이 1000원 단위일 시 정상")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceExactlyDividedByThousand() {
        assertThatCode(() -> new UserPurchase(10000, 3))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 시 에러")
    @Test
    void Should_ThrowException_When_PurchasePriceNotDividedByThousand() {
        Assertions.assertThatThrownBy(() ->
                new UserPurchase(1200, 3)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액보다 수동 티켓 총 금액이 더 많을 시 에러")
    @Test
    void Should_ThrowException_When_ManualTicketPriceGreaterThanPurchasePrice() {
        Assertions.assertThatThrownBy(() ->
                new UserPurchase(2000, 3)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위일 때 티켓 개수 확인")
    @Test
    void Should_ReturnNumberOfTickets_When_ExactlyDividedByThousand() {
        UserPurchase userPurchase = new UserPurchase(21000, 3);
        assertThat(userPurchase.getManualTicketCount()).isEqualTo(3);
        assertThat(userPurchase.getAutoTicketCount()).isEqualTo(18);
    }
}
