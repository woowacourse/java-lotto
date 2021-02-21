package lotto.domain.ticketpurchase;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchasePriceTest {
    @DisplayName("구입 금액이 1000원 단위일 때 정상")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceIsDividedBy1000() {
        assertThatCode(() -> new PurchasePrice(1000)).doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 0원 이하일 때 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void Should_ThrowException_When_PurchasePriceIsZeroOrLess(int purchasePrice) {
        assertThatThrownBy(() -> new PurchasePrice(purchasePrice))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 때 때 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 1001, 2005})
    void Should_ThrowException_When_PurchasePriceIsNotDividedBy1000(int purchasePrice) {
        assertThatThrownBy(() -> new PurchasePrice(purchasePrice))
            .isInstanceOf(IllegalArgumentException.class);
    }
}