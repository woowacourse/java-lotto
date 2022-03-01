package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualPurchaseCountTest {

    @DisplayName("입력한 숫자로 ManualPurchaseCount 객체를 생성한다")
    @Test
    void manualPurchaseCount_constructor_test() {
        assertThatNoException()
                .isThrownBy(() -> new ManualPurchaseCount(10000));
    }

    @DisplayName("수동 구매를 할 수 있다.")
    @Test
    void canBuy_true_test() {
        ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(1);

        assertThat(manualPurchaseCount.canBuy()).isTrue();
    }

    @DisplayName("수동 구매를 할 수 없다.")
    @Test
    void canBuy_false_test() {
        ManualPurchaseCount manualPurchaseCount = new ManualPurchaseCount(0);

        assertThat(manualPurchaseCount.canBuy()).isFalse();
    }

    @DisplayName("음수를 입력했을 때 IllegalArgumentException 예외가 발생한다")
    @Test
    void manualPurchaseCount_constructor_error_on_negative_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new ManualPurchaseCount(-1000))
                .withMessage("수동 로또 구매 횟수는 음수로 입력하면 안됩니다.");
    }
}
