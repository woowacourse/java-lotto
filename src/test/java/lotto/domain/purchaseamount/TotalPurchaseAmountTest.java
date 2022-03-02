package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalPurchaseAmountTest {
    private final TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
            .setLottoPrice(1000)
            .setTotalAmount("8000")
            .setManualPurchaseAmount("2")
            .build();

    @Test
    @DisplayName("전체 구매 개수를 반환한다.")
    void getCountOfTotalLottoNumbers_Test() {
        //given
        final int expected = 8;
        //when
        final int actual = totalPurchaseAmount.getCountOfTotalLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("자동 구매 개수를 반환한다.")
    void getCountOfAutoLottoNumbers_Test() {
        //given
        final int expected = 6;
        //when
        final int actual = totalPurchaseAmount.getCountOfAutoLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 구매 개수를 반환한다.")
    void getCountOfManualLottoNumbers_Test() {
        //given
        final int expected = 2;
        //when
        final int actual = totalPurchaseAmount.getCountOfManualLottoNumbers();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 수익을 받아 수익률을 반환한다.")
    void getProfitRate_Test() {
        //given
        final long totalProfit = 4000000000L;
        final double expected = (double) totalProfit / 8000;
        //when
        final double actual = totalPurchaseAmount.getProfitRate(totalProfit);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("로또 가격을 지정하지 않을 시, 1000의 배수가 아닌 값으로 객체 생성할 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"abc", "1004", "-1000", "12.334"})
    void create_exceptionByInvalidPurchaseAmountValue_defaultPrice_Test(final String invalidValue) {
        //given
        final String expectedExceptionMessage = "구매 금액은 로또 가격의 양의 배수여야 합니다.";
        final int anyLottoPrice = 1000;
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
                        .setLottoPrice(anyLottoPrice)
                        .setTotalAmount(invalidValue)
                        .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("로또 가격을 지정할 경우, 총 구매 금액이 로또 가격의 배수가 아닐 경우 예외를 발생시킨다.")
    void create_exceptionByInvalidPurchaseAmountValue_decidedPrice_Test() {
        //given
        final String expectedExceptionMessage = "구매 금액은 로또 가격의 양의 배수여야 합니다.";
        final String invalidValue = "3000";
        final int lottoPrice = 2000;
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
                        .setLottoPrice(lottoPrice)
                        .setTotalAmount(invalidValue)
                        .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("구매 금액을 지정하기 전에 수동 구매 개수를 지정하면 예외를 발생시킨다.")
    void setManualPurchaseAmount_exceptionByNotDecidedTotalPurchaseAmount_Test() {
        final String anyManualPurchaseCount = "3";
        final String expectedExceptionMessage = "구매 금액을 먼저 지정해야 수동 구매 로또 수를 입력할 수 있습니다.";
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount.TotalPurchaseAmountBuilder()
                        .setManualPurchaseAmount(anyManualPurchaseCount)
                        .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
