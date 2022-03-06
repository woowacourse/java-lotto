package lotto.domain.matchkind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class WinningKindTest {
    @ParameterizedTest
    @DisplayName("매치 개수를 받아 개수에 따른 당첨 종류를 반환한다.")
    @CsvSource({"1, true, LOWER_THAN_THREE", "2, true, LOWER_THAN_THREE",
            "3, false, THREE", "4, false, FOUR", "5, false ,FIVE", "5, true, FIVE_BONUS", "6, false ,SIX",
            "3, true, THREE", "4, true, FOUR"})
    void from_Test(final int matchCount, final boolean bonusHit, final WinningKind expected) {
        //when
        final WinningKind actual = WinningKind.of(matchCount, bonusHit);
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
