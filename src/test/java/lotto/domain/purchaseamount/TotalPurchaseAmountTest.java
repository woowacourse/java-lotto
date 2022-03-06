package lotto.domain.purchaseamount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalPurchaseAmountTest {
    private final TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount(8000);

    @Test
    @DisplayName("로또 총 구매 개수를 반환한다.")
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
    @DisplayName("1000(로또 가격)의 배수가 아닌 값으로 객체 생성할 경우 예외를 발생시킨다.")
    void create_exceptionByInvalidPurchaseAmountValue_defaultPrice_Test() {
        //given
        final int invalidValue = 5050;
        final String expectedExceptionMessage = "구매 금액은 로또 가격의 양의 배수여야 합니다.";
        //when then
        assertThatThrownBy(
                () -> new TotalPurchaseAmount(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
