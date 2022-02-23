package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {
    @ParameterizedTest
    @DisplayName("1000의 양의 배수가 아닌 값으로 생성할 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"abc", "1004", "-1000", "12.334"})
    void create_exceptionByInvalidPurchaseAmountValue(final String invalidValue) {
        //given
        final String expectedExceptionMessage = "구매 금액은 1000의 양의 배수여야 합니다.";
        final int lottoPrice = 1000;
        //when then
        assertThatThrownBy(
                () -> PurchaseAmount.fromPurchaseAmountAndLottoPrice(invalidValue, lottoPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("총 수익을 받아 수익률을 반환한다.")
    void getProfitRate() {
        //given
        final int anyLottoPrice = 1000;
        final PurchaseAmount purchaseAmount =
                PurchaseAmount.fromPurchaseAmountAndLottoPrice("2000", anyLottoPrice);
        final long totalProfit = 4000000000L;
        final double expected = (double) totalProfit / 2000;
        //when
        final double actual = purchaseAmount.getProfitRate(totalProfit);
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
