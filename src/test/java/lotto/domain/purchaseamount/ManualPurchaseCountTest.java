package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualPurchaseCountTest {
    private final int totalPurchaseCount = 15;
    @ParameterizedTest
    @DisplayName("수동 구매 개수가 0 보다 작거나 전체 구매 개수보다 크면 예외를 발생시킨다.")
    @ValueSource(ints = {-1, 16})
    void create_exceptionByNotZeroOrNaturalNumber_Test(final int invalidManualPurchaseCount) {
        //given
        final String expectedExceptionMessage = "수동 구매 개수는 0 이상, 전체 구매 개수 사이의 정수여야 합니다.";
        //when then
        assertThatThrownBy(() -> new ManualPurchaseCount(invalidManualPurchaseCount, totalPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
