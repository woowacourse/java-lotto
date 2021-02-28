package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AutoPurchaseCountTest {

    @DisplayName("자동 로또 구매 갯수 확인")
    @Test
    void autoLottoPurchaseCount() {
        AutoPurchaseCount autoPurchaseCount = new AutoPurchaseCount(3);

        assertThat(autoPurchaseCount.getAutoPurchaseCount()).isEqualTo(3);
    }

    @DisplayName("돈보다 많은 구매를 갯수를 구매할 경우")
    @Test
    void minusInputCheck() {
        assertThatThrownBy(() -> {
            new AutoPurchaseCount(-1);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매금액을 초과하였습니다. 갯수를 확인해주세요. ");
    }
}
