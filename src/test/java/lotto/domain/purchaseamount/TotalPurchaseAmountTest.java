package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalPurchaseAmountTest {
    private final TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount.Builder()
            .setLottoPrice(1000)
            .setTotalAmount(8000)
            .build();

    @Test
    @DisplayName("전체 구매 개수를 반환한다.")
    void getCountOfTotalLottoNumbers_Test() {
        //given
        final int expected = 8;
        //when
        final int actual = totalPurchaseAmount.getTotalPurchaseCount();
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

    @Test
    @DisplayName("로또 가격을 재정의하지 않을 시, 1000의 배수가 아닌 값으로 객체 생성할 경우 예외를 발생시킨다.")
    void create_exceptionByInvalidPurchaseAmountValue_defaultPrice_Test() {
        //given
        final int invalidValue = 5050;
        final String expectedExceptionMessage = "구매 금액은 로또 가격의 양의 배수여야 합니다.";
        final int anyLottoPrice = 1000;
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount.Builder()
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
        final int invalidValue = 3000;
        final int lottoPrice = 2000;
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount.Builder()
                        .setLottoPrice(lottoPrice)
                        .setTotalAmount(invalidValue)
                        .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
