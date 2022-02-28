package lotterymachine.vo;

import lotterymachine.LotteryPurchase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryPurchaseTest {

    @Test
    void getCount() {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(14000);
        assertThat(lotteryPurchase.getCount()).isEqualTo(14);
    }

    @Test
    void getPurchasePrice() {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(14500);
        assertThat(lotteryPurchase.getPurchasePrice()).isEqualTo(14000);
    }
}