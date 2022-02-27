package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @Test
    void of_테스트_1등() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    void of_테스트_2등() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void of_테스트_3등() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    void of_테스트_꼴등() {
        Rank rank = Rank.of(1, true);
        assertThat(rank).isEqualTo(Rank.NO_PRIZE);
    }

    @Test
    void Default_제외_값_받아옴() {
        List<Rank> ranks = Rank.getWithoutDefault();
        assertThat(ranks).doesNotContain(Rank.NO_PRIZE);
    }
}