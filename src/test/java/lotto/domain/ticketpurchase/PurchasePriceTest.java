package lotto.domain.ticketpurchase;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.exception.InvalidPurchasePriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchasePriceTest {
    @DisplayName("구매 금액이 1000원 단위일 때 정상")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceIsDividedBy1000() {
        assertThatCode(() -> new PurchasePrice(1000)).doesNotThrowAnyException();
    }

    @DisplayName("구매 금액이 0원 이하일 때 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void Should_ThrowException_When_PurchasePriceIsZeroOrLess(int purchasePrice) {
        assertThatThrownBy(() -> new PurchasePrice(purchasePrice))
            .isInstanceOf(InvalidPurchasePriceException.class);
    }

    @DisplayName("구매 금액이 1000원 단위가 아닐 때 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 1001, 2005})
    void Should_ThrowException_When_PurchasePriceIsNotDividedBy1000(int purchasePrice) {
        assertThatThrownBy(() -> new PurchasePrice(purchasePrice))
            .isInstanceOf(InvalidPurchasePriceException.class);
    }

    @DisplayName("구매 금액 반환 테스트")
    @Test
    void Should_Return_ExpectedPrice_When_GetPrice() {
        int price = 1000;
        PurchasePrice purchasePrice = new PurchasePrice(price);

        assertThat(purchasePrice.getPrice()).isEqualTo(price);
    }

    @DisplayName("전체 구매 티켓 개수 반환 테스트")
    @Test
    void Should_Return_ExpectedAllTicketsSize_When_AllTicketsSize() {
        int allTicketsSize = 4;
        PurchasePrice purchasePrice = new PurchasePrice(1000 * allTicketsSize);

        assertThat(purchasePrice.allTicketsSize()).isEqualTo(allTicketsSize);
    }
}