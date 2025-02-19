package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @DisplayName("조건에 맞는 당첨 등수를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"6, false, FIRST_PLACE", "5, true, SECOND_PLACE", "5, false, THIRD_PLACE",
            "4, false, FOURTH_PLACE", "3, false, FIFTH_PLACE", "3, true, FIFTH_PLACE", "4, true, FOURTH_PLACE"})
    void matchRankTest(int matchCount, boolean isBonusMatch, String expectedPlace) {
        Rank matchedRank = Rank.getPlace(matchCount, isBonusMatch);

        final Rank expectedRank = Arrays.stream(Rank.values())
                .filter(p -> p.name().equals(expectedPlace))
                .findAny()
                .orElse(null);

        assertThat(matchedRank).isEqualTo(expectedRank);
    }

    @DisplayName("로또 미당첨 시 6등을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"2, true", "1, true", "2, false", "1, false"})
    void sixthPlaceTest(int matchCount, boolean isBonusMatch) {
        Rank rank = Rank.getPlace(matchCount, isBonusMatch);

        assertThat(rank).isEqualTo(Rank.SIXTH_PLACE);
    }
}
