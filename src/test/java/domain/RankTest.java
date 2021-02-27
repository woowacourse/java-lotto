package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 1등")
    @Test
    public void firstRankFromTest() {
        Rank rank = Rank.from(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 2등")
    @Test
    public void secondRankFromTest() {
        Rank rank = Rank.from(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 3등")
    @Test
    public void thirdRankFromTest() {
        Rank rank = Rank.from(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 4등")
    @Test
    public void fourthRankFromTest() {
        Rank rank = Rank.from(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 5등")
    @Test
    public void fifthRankFromTest() {
        Rank rank = Rank.from(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("로또 번호를 맞춘 개수에 맞는 Rank 객체가 리턴되는지 테스트한다. - 0 ~ 2개 맞출 시 없음")
    @Test
    public void noRankFromTwoMatchingCountTest() {
        for (int i = 0; i < 3; i++) {
            Rank rank = Rank.from(i, false);
            assertThat(rank).isEqualTo(Rank.NOTHING);
        }
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

    @DisplayName("3등 상금 리턴 테스트")
    @Test
    public void compareThirdRewardTest() {
        Rank rank = Rank.from(5, false);
        assertThat(rank.getReward()).isEqualTo(Money.from(1_500_000));
    }

    @DisplayName("4등 상금 리턴 테스트")
    @Test
    public void compareFourthRewardTest() {
        Rank rank = Rank.from(4, false);
        assertThat(rank.getReward()).isEqualTo(Money.from(50_000));
    }

    @DisplayName("5등 상금 리턴 테스트")
    @Test
    public void compareFifthRewardTest() {
        Rank rank = Rank.from(3, false);
        assertThat(rank.getReward()).isEqualTo(Money.from(5_000));
    }

    @DisplayName("Nothing Rank 상금 리턴 테스트")
    @Test
    public void noRewardOnNothingRankTest() {
        for (int i = 0; i < 3; i++) {
            Rank rank = Rank.from(i, false);
            assertThat(rank.getReward()).isEqualTo(Money.from(0));
        }
    }
}
