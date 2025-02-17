package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @DisplayName("구입 금액이 1_000원 미만이므로 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100, 999})
    void validateRangeTest(final int number) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new PurchaseAmount(number)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

    }

    @DisplayName("구입 금액이 1_000원 단위가 아니므로 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1100, 1200, 10001, 21300})
    void validateAmountTest(final int number) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new PurchaseAmount(number)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");

    }

    @DisplayName("구입 금액이 1_000원 이상이고 1_000원 단위이면 정상적으로 생성된다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 5000, 10000})
    void createPurchaseAmountTest(final int number) {
        //given
        //when
        PurchaseAmount purchaseAmount = new PurchaseAmount(number);
        //then
        assertThat(purchaseAmount.getAmount()).isEqualTo(number);
    }

}
