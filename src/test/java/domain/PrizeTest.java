package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizeTest {
    @ParameterizedTest
    @CsvSource(value = {"6, false, FIRST_PLACE", "5, true, SECOND_PLACE", "5, false, THIRD_PLACE",
            "4, false, FOURTH_PLACE", "3, false, FIFTH_PLACE", "3, true, FIFTH_PLACE", "4, true, FOURTH_PLACE"})
    void matchPrizeTest(int matchCount, boolean isBonusMatch, String expectedPrizeName) {
        Prize matchedPrize = Prize.getPrizePlace(matchCount, isBonusMatch);

        final Prize expectedPrize = Arrays.stream(Prize.values())
                .filter(p -> p.name().equals(expectedPrizeName))
                .findAny()
                .orElse(null);

        assertThat(matchedPrize).isEqualTo(expectedPrize);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, true", "1, true", "2, false", "1, false"})
    void sixthPrizeTest(int matchCount, boolean isBonusMatch) {
        Prize prizePlace = Prize.getPrizePlace(matchCount, isBonusMatch);

        assertThat(prizePlace).isEqualTo(Prize.SIXTH_PLACE);
    }
}
