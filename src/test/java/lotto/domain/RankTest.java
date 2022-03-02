package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @DisplayName("보너스 볼과 5개를 맞춘 경우 2등이어야한다.")
    @Test
    void 이등_보너스_포함_당첨() {
        // given
        Rank rank = Rank.of(5, true);

        // when & then
        assertAll(
                () -> assertThat(rank).isEqualTo(Rank.SECOND),
                () -> assertThat(rank.getPrizeMoney()).isEqualTo(30_000_000)
        );
    }

    @DisplayName("보너스 볼을 포함하지 않고 5개를 맞춘 경우 3등이어야한다.")
    @Test
    void 삼등_보너스_미포함_당첨() {
        // given
        Rank rank = Rank.of(5, false);

        // when & then
        assertAll(
                () -> assertThat(rank).isEqualTo(Rank.THIRD),
                () -> assertThat(rank.getPrizeMoney()).isEqualTo(1_500_000)
        );
    }

    @DisplayName("보너스볼 미포함하여 6개 맞춘 경우 1등이어야 한다.")
    @Test
    void 일등_보너스_미포함_당첨() {
        // given
        Rank rank = Rank.of(6, true);

        // when & then
        assertAll(
                () -> assertThat(rank).isEqualTo(Rank.FIRST),
                () -> assertThat(rank.getPrizeMoney()).isEqualTo(2_000_000_000)
        );
    }

    @DisplayName("2개 맞춘 경우 NOTHING 이다.")
    @Test
    void 두개_맞은_경우_NOTTING() {
        // given
        Rank rank = Rank.of(2, true);

        // when & then
        assertThat(rank).isEqualTo(Rank.NOTHING);
    }
}