package domain;

import static lotto.domain.PurchaseCount.PURCHASE_COUNT_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.PurchaseCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseCountTest {

    @Test
    @DisplayName("구매 개수는 1개 이상일떄 생성된다.")
    void testCreatePurchaseCount() {
        assertThat(new PurchaseCount(1).getValue()).isEqualTo(1);
    }

    @Test
    @DisplayName("구매 개수는 1개 이상이 아니면 예외가 발생한다.")
    void testCreatePurchaseCountException() {
        assertThatThrownBy(() -> new PurchaseCount(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(PURCHASE_COUNT_ERROR);
    }

}
