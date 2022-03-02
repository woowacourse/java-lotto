package lotterymachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryPurchaseTest {

    @Test
    @DisplayName("총 투입 금액과 수동 구매 갯수 입력하여 생성한다.")
    void create() {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(2000, 1);
        assertThat(lotteryPurchase.getAutoCount()).isEqualTo(1);
        assertThat(lotteryPurchase.getPassivityCount()).isEqualTo(1);
    }
}