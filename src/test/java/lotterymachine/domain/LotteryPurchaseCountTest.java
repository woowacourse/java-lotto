package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LotteryPurchaseCountTest {

    @Test
    @DisplayName("수동 로또 구매 개수와 총 로또 구매 개수를 입력 받아, Count를 생성한다.")
    void create() {
        LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(3, 11, 14);
        assertThat(lotteryPurchaseCount.getAutoValue()).isEqualTo(11);
        assertThat(lotteryPurchaseCount.getPassivityValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 구매 개수가 총 로또 구매 개수 보다 높을 시, 에러가 발생한다.")
    void validatePassivityValue() {
        assertThatThrownBy(() -> {
            LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(3, 3, 5);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 구매 개수가 총 구매 개수 보다 높습니다.");
    }
}
