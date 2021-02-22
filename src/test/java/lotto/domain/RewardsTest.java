package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RewardsTest {

    private static Rewards rewards;

    @BeforeEach
    void setUp() {
        rewards = new Rewards(Arrays.asList(Reward.FIRST, Reward.SECOND, Reward.SECOND));
    }

    @DisplayName("포함된 개수 테스트")
    @Test
    void rewordRankCount() {
        assertThat(rewards.getRankCount(Reward.FIRST)).isEqualTo(1);
        assertThat(rewards.getRankCount(Reward.SECOND)).isEqualTo(2);
        assertThat(rewards.getRankCount(Reward.THIRD)).isEqualTo(0);
    }

    @DisplayName("수익률 테스트")
    @Test
    void profit() {
        assertThat(rewards.profit(10_000_000)).isEqualTo(206);
    }
}
