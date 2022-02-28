package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseCountTest {

    @Test
    @DisplayName("수동으로 구매할 로또 수를 입력받는 기능")
    void createLottoPurchaseCount() {
        LottoPurchaseCount lottoPurchaseCount = new LottoPurchaseCount(2, 3);
        
        assertThat(lottoPurchaseCount).isEqualTo(new LottoPurchaseCount(2, 3));
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수의 범위가 유효하지 않은 경우")
    void createLottoInvalidRangePurchaseCount() {
        assertThatThrownBy(() ->
            new LottoPurchaseCount(-1, 2))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
