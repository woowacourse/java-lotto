package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RewardTest {

    @Test
    @DisplayName("상금은 0이상이어야 한다.")
    void throwExceptionWhenNegative() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Reward(-1L))
                .withMessageMatching("상금은 0이상이어야 한다.");
    }

    @Test
    @DisplayName("상금은 int의 범위를 넘어설 수 있다.")
    void checkOverInteger() {
        assertThat(new Reward((long) Integer.MAX_VALUE + 1)).isNotNull();
    }

    @Test
    @DisplayName("상금의 합을 구할 수 있다.")
    void plusReward() {
        Reward reward1 = new Reward(2_000_000_000L);
        Reward reward2 = new Reward(2_000_000_000L);
        reward1.plus(reward2);

        assertThat(reward1).isEqualTo(new Reward(4_000_000_000L));
    }

    @Test
    @DisplayName("상금에서 돈을 나누면 비율을 반환한다.")
    void divideMoney() {
        Reward reward = new Reward(50_000L);
        LottoMoney money = LottoMoney.createLottoMoney(3_000);

        assertThat(reward.divide(money)).isEqualTo(BigDecimal.valueOf(16.66));
    }
}
