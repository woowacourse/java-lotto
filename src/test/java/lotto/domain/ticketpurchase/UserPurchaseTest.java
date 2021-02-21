package lotto.domain.ticketpurchase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserPurchaseTest {
    @DisplayName("구입 금액이 1000원 단위일 시 정상")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceExactlyDividedByThousand() {
        assertThatCode(() -> new UserPurchase(1000, new LottoTickets()))
            .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 시 에러")
    @Test
    void Should_ThrowException_When_PurchasePriceNotDividedByThousand() {
        Assertions.assertThatThrownBy(() ->
            new UserPurchase(1200, new LottoTickets())
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위일 때 티켓 개수 확인")
    @Test
    void Should_ReturnNumberOfTickets_When_ExactlyDividedByThousand() {
        UserPurchase userPurchase = new UserPurchase(21000, new LottoTickets());
        assertThat(userPurchase.getNumberOfAllTickets()).isEqualTo(21);
    }
}
