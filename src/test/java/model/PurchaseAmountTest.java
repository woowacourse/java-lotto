package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constans.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @Nested
    @DisplayName("유효한 경우의 테스트")
    class ValidCases {

        @DisplayName("구매 금액이 올바르게 생성 된다.")
        @Test
        void createPurchaseAmount() {
            // given
            int money = 1000;

            // when
            PurchaseAmount actual = new PurchaseAmount(money);

            // then
            assertThat(actual.getMoney()).isEqualTo(money);
        }

    }

    @Nested
    @DisplayName("유효하지 않은 경우의 테스트")
    class InvalidCases {

        @DisplayName("구매 금액이 양수가 아니라면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        void validateNotPositive(final int money) {
            // given & when & then
            assertThatThrownBy(() -> new PurchaseAmount(money))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.PURCHASE_AMOUNT_POSITIVE.getMessage());
        }

        @DisplayName("구매 금액이 양수가 아니라면 예외가 발생한다.")
        @Test
        void validateNotDivideLottoPrice() {
            // given & when & then
            assertThatThrownBy(() -> new PurchaseAmount(Lotto.LOTTO_PRICE + 1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorType.PURCHASE_AMOUNT_NOT_DIVIDE_LOTTO_PRICE.getMessage());
        }
    }
}