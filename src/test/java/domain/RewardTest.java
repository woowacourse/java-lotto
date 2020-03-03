package domain;

import Lotto.domain.Reward;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RewardTest {
    @Test
    @DisplayName("reward init테스트-정상 입력")
    void rightInputInit() {
        Reward reward = new Reward(1_000_000);
        assertThat(reward.getReward()).isEqualTo(1_000_000);
    }

    @ParameterizedTest
    @DisplayName("reward init 양수가 아닌 수 입력")
    @ValueSource(ints = {0, -1, -22})
    void wrongInputInit() {
        assertThatThrownBy(() -> new Reward(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커야");
    }
}
