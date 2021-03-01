package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.InvalidLottoHitCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RewardTest {

    private Reward reward;

    @BeforeEach
    void setUp() {
        reward = Reward.THIRD;
    }

    @DisplayName("맞춘 공의 개수 테스트")
    @Test
    void hitCountTest() {
        assertThat(reward.getHitCount()).isEqualTo(5);
    }

    @DisplayName("당첨 금액 테스트")
    @Test
    void winningMoneyTest() {
        assertThat(reward.getWinningMoney()).isEqualTo(1_500_000);
    }

    @DisplayName("등수 반환 테스트")
    @Test
    void valueOfTest() {
        assertThat(Reward.valueOf(6, false)).isEqualTo(Reward.FIRST);
        assertThat(Reward.valueOf(5, true)).isEqualTo(Reward.SECOND);
        assertThat(Reward.valueOf(5, false)).isEqualTo(Reward.THIRD);
        assertThat(Reward.valueOf(3, false)).isEqualTo(Reward.FIFTH);
        assertThat(Reward.valueOf(3, true)).isEqualTo(Reward.FIFTH);
    }

    @DisplayName("hitCount 에 따른 에러테스트")
    @Test
    void validate() {
        assertThatThrownBy(() -> Reward.valueOf(-1, false)).isInstanceOf(
            InvalidLottoHitCountException.class);
        assertThatThrownBy(() -> Reward.valueOf(7, false)).isInstanceOf(
            InvalidLottoHitCountException.class);
    }
}