package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액을 생성한다")
    void makePurchaseAmount() {
        String input = "14000";
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14000);
    }

    @Test
    @DisplayName("구입 금액은 1000원 단위로 생성된다")
    void makePurchaseAmount1000() {
        String input = "14500";
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14000);
    }

    @Test
    @DisplayName("구입 금액이 1000원 미만일 경우 예외를 발생시킨다.")
    void throwExceptionWhenInputLessThan1000() {
        String input = "500";
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsNotNumber() {
        String input = "notNumber";
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 숫자여야합니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("구입 금액이 empty, blank일 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsEmptyOrBlank(String input) {
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 공백일 수 없습니다");
    }
}
