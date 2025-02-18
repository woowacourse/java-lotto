package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoAwardTest {

    @ParameterizedTest
    @CsvSource({
            "3, false, FIFTH_RANK",
            "4, false, FOURTH_RANK",
            "5, false, THIRD_RANK",
            "5, true, SECOND_RANK",
            "6, false, FIRST_RANK"
    })
    void 매칭_횟수와_보너스_볼에_일치하는_당첨_상금을_조회한다(final int matchingCount, final boolean matchesBonusNumber,
                                        final String lottoAwardName) {
        // Given

        // When
        LottoAward lottoAward = LottoAward.from(matchingCount, matchesBonusNumber);

        // Then
        Assertions.assertThat(lottoAward.name()).isEqualTo(lottoAwardName);
    }
}
