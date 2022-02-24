package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMatchKindTest {
    @ParameterizedTest
    @DisplayName("매치 개수를 받아 개수에 따른 당첨 종류 객체를 반환한다.")
    @CsvSource({"3, false, THREE", "4, false, FOUR", "5, false ,FIVE", "5, true, FIVE_BONUS", "6, false ,SIX"})
    void from(final int matchCount, final boolean bonus, final LottoMatchKind expected) {
        //when
        final LottoMatchKind actual = LottoMatchKind.from(matchCount, bonus);
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
