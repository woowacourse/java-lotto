package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.enumeration.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("보너스볼 포함하여 6개 맞춘 경우 1등이어야 한다.")
    @Test
    void checkFirstPlaceWithBonusBall() {
        // given
        Rank rank = Rank.of(6, false);

        // when & then
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000);
    }

    @DisplayName("보너스볼 미포함하여 6개 맞춘 경우 1등이어야 한다.")
    @Test
    void checkFirstPlace() {
        // given
        Rank rank = Rank.of(6, true);

        // when & then
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000);
    }

    @DisplayName("보너스 볼을 포함하여 5개를 맞춘 경우 2등이어야한다.")
    @Test
    void checkSecondPlace() {
        // given
        Rank rank = Rank.of(5, true);

        // when & then
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000);
    }

    @DisplayName("보너스 볼을 포함하지 않고 5개를 맞춘 경우 3등이어야한다.")
    @Test
    void checkThirdPlace() {
        // given
        Rank rank = Rank.of(5, false);

        // when & then
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrizeMoney()).isEqualTo(1_500_000);
    }

    @DisplayName("2개 맞춘 경우 NOTTING 이다.")
    @Test
    void checkNothing() {
        // given
        Rank rank = Rank.of(2, true);

        // when & then
        assertThat(rank).isEqualTo(Rank.NOTHING);
    }
}