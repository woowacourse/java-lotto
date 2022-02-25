package domain;

import static common.DisplayFormat.PARAMETERIZED_TEST_DISPLAY_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoResultTest {

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(booleans = {true, false})
    void of_sixMatchingNumbers(boolean hasBonus) {
        LottoResult actual = LottoResult.of(6, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FIRST);
    }

    @Test
    void of_fiveMatchingNumbersWithBonusNumber() {
        LottoResult actual = LottoResult.of(5, true);

        assertThat(actual).isEqualTo(LottoResult.SECOND);
    }

    @Test
    void of_fiveMatchingNumbersWithoutBonusNumber() {
        LottoResult actual = LottoResult.of(5, false);

        assertThat(actual).isEqualTo(LottoResult.THIRD);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(booleans = {true, false})
    void of_fourMatchingNumbers(boolean hasBonus) {
        LottoResult actual = LottoResult.of(4, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FOURTH);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @ValueSource(booleans = {true, false})
    void of_threeMatchingNumbers(boolean hasBonus) {
        LottoResult actual = LottoResult.of(3, hasBonus);

        assertThat(actual).isEqualTo(LottoResult.FIFTH);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_DISPLAY_FORMAT)
    @CsvSource(value = {"0,true", "0,false", "1,true", "1,false", "2,true", "2,false"})
    void of_noMatchNumbers(int matchCount, boolean hasBonus) {
        LottoResult actual = LottoResult.of(matchCount, hasBonus);

        assertThat(actual).isEqualTo(null);
    }
}
