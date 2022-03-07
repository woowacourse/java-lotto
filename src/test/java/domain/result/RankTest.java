package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @ParameterizedTest
    @CsvSource(value = {
            "6, false, FIRST"
            , "5, true, SECOND"
            , "5, false, THIRD"
            , "4, true, FOURTH"
            , "4, false, FOURTH"
            , "3, true, FIFTH"
            , "3, false, FIFTH"
            , "2, false, NONE"
            , "2, true, NONE"
    })
    void 랭킹_테스트(int matchCount, boolean isBonus, Rank expectedRank) {
        Rank rank = Rank.of(matchCount, isBonus);
        assertThat(rank)
                .isEqualTo(expectedRank);
    }
}