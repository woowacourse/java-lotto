package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Rank;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @Test
    void 매치_결과_등수없음_반환_테스트() {
        assertThat(Rank.getMatchResult(2, true))
                .isEqualTo(Rank.MATCH_ZERO_NUMBERS);
        assertThat(Rank.getMatchResult(2, false))
                .isEqualTo(Rank.MATCH_ZERO_NUMBERS);
    }

    @Test
    void 매치_결과_2등_반환_테스트() {
        assertThat(Rank.getMatchResult(5, true))
                .isEqualTo(Rank.MATCH_FIVE_AND_BONUS_NUMBERS);
    }

    @Test
    void 매치_결과_3등_반환_테스트() {
        assertThat(Rank.getMatchResult(5, false))
                .isEqualTo(Rank.MATCH_FIVE_NUMBERS);
    }
}
