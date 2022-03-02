package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualPurchaseCountTest {
    private final TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount("14000", 1000);

    @ParameterizedTest
    @DisplayName("수동 구매 개수가 0 혹은 자연수가 아니면 예외를 발생시킨다.")
    @ValueSource(strings = {"-1", "1.2", "a", "!"})
    void create_exceptionByNotZeroOrNaturalNumber_Test(final String invalidValue) {
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCount(invalidValue, totalPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 로또 수는 자연수여야합니다.");
    }

    @Test
    @DisplayName("수동 구매 개수가 전체 로또 개수보다 크면 예외를 발생시킨다.")
    void create_exceptionByLowerTest() {
        //given
        final String higherManualPurchaseCountThanTotalCount = "15";
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCount(higherManualPurchaseCountThanTotalCount, totalPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("총 구매 수보다 수동 구매 수가 큽니다.");
    }
}
