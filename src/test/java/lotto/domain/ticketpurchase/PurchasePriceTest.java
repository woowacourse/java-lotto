package lotto.domain.ticketpurchase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PurchasePriceTest {
    @DisplayName("구매 금액 객체 생성 테스트")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceExactlyDividedByThousand() {
        assertThatCode(() -> new PurchasePrice(10000)).doesNotThrowAnyException();
        PurchasePrice purchasePrice = new PurchasePrice(10000);
        Assertions.assertThat(purchasePrice.getPurchasePrice()).isEqualTo(10000);
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 시 에러")
    @Test
    void Should_ThrowException_When_PurchasePriceNotDividedByThousand() {
        Assertions.assertThatThrownBy(
                () -> new PurchasePrice(1400)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
