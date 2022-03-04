package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualPurchaseCountTest {
    private final int totalPurchaseCount = 15;
    @Test
    @DisplayName("수동 구매 개수가 0 보다 작으면 예외를 발생시킨다.")
    void create_exceptionByNotZeroOrNaturalNumber_Test() {
        //given
        final int invalidManualPurchaseCount = -1;
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCount(invalidManualPurchaseCount, totalPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 로또 수는 자연수여야합니다.");
    }

    @Test
    @DisplayName("수동 구매 개수가 전체 로또 개수보다 크면 예외를 발생시킨다.")
    void create_exceptionByLowerTest() {
        //given
        final int invalidManualPurchaseCount = 16;
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCount(invalidManualPurchaseCount, totalPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("총 구매 수보다 수동 구매 수가 큽니다.");
    }
}
