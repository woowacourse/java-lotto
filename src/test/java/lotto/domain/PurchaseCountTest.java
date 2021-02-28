package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseCountTest {

    @DisplayName("수동 구매 로또 갯수 저장 확인")
    @Test
    void manualPurchaseCount() {
        PurchaseCount purchaseCount = new PurchaseCount(3, 3);

        assertThat(purchaseCount.getManualPurchaseCount()).isEqualTo(3);
    }

    @DisplayName("0이하의 숫자를 입력하였을 경우 확인")
    @Test
    void minusInputCheck() {
        assertThatThrownBy(() -> {
            new PurchaseCount(-1, 0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0이상의 숫자를 입력하세요. ");
    }

    @DisplayName("총 구매갯수보다 많은 수의 수동로또를 구매할 경우")
    @Test
    void buyMoreThanTotal() {
        assertThatThrownBy(() -> {
            new PurchaseCount(3, 4);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("총 구매갯수보다 더많은 수동 로또를 구매할 수 없습니다.");
    }

}
