package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultTest {

    @DisplayName("1등 당첨")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(booleans = {true, false})
    void of_SixMatchingNumbers_ReturnsFirstPrize(boolean hasBonus) {
        LottoResult actual = LottoResult.of(6, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FIRST);
    }

    @DisplayName("2등 당첨")
    @Test
    void of_FiveMatchingNumbersWithBonusNumber_ReturnsSecondPrize() {
        LottoResult actual = LottoResult.of(5, true);

        assertThat(actual).isEqualTo(LottoResult.SECOND);
    }

    @DisplayName("3등 당첨")
    @Test
    void of_FiveMatchingNumbersWithoutBonusNumber_ReturnsThirdPrize() {
        LottoResult actual = LottoResult.of(5, false);

        assertThat(actual).isEqualTo(LottoResult.THIRD);
    }

    @DisplayName("4등 당첨")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(booleans = {true, false})
    void of_FourMatchingNumbers_ReturnsFourthPrize(boolean hasBonus) {
        LottoResult actual = LottoResult.of(4, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FOURTH);
    }

    @DisplayName("5등 당첨")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @ValueSource(booleans = {true, false})
    void of_ThreeMatchingNumbers_ReturnsFifthPrize(boolean hasBonus) {
        LottoResult actual = LottoResult.of(3, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FIFTH);
    }

    @DisplayName("낙첨")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"0,true", "0,false", "1,true", "1,false", "2,true", "2,false"})
    void of_NoMatchNumbers_ReturnsNull(int matchCount, boolean hasBonus) {
        LottoResult actual = LottoResult.of(matchCount, hasBonus);

        assertThat(actual).isEqualTo(null);
    }
}
