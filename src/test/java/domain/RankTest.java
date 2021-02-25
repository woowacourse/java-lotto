package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다.")
    @Test
    public void rankFromTest() {
        Rank rank = Rank.from(6, false);
        assertThat(rank).isInstanceOf(Rank.class);
    }

    @DisplayName("1등 상금 리턴 테스트")
    @Test
    public void compareFirstRewardTest() {
        Rank rank = Rank.from(6, false);
        assertThat(rank.getReward()).isEqualTo(Money.from(2_000_000_000));
    }

    @DisplayName("2등 상금 리턴 테스트")
    @Test
    public void compareSecondRewardTest() {
        Rank rank = Rank.from(5, true);
        assertThat(rank.getReward()).isEqualTo(Money.from(30_000_000));
    }

    @DisplayName("상금이 없는 경우")
    @Test
    public void compareNoRewardTest() {
        Rank rank = Rank.from(1, false);
        assertThat(rank.getReward()).isEqualTo(Money.from(0));
    }
}
