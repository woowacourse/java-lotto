package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTypeTest {

    @ParameterizedTest
    @CsvSource({
            "true, 5, SECOND", // 보너스 볼 + 5개 일치 -> 2등
            "false, 5, THIRD", // 5개 일치 -> 3등
            "true, 6, FIRST",  // 6개 일치 -> 1등
            "false, 6, FIRST", // 6개 일치 -> 1등
            "false, 4, FOURTH",// 4개 일치 -> 4등
            "false, 3, FIFTH", // 3개 일치 -> 5등
            "true, 0, NONE"    // 0개 일치 -> 당첨 X
    })
    @DisplayName("로또 결과에 해당하는 등수를 반환한다.")
    void 로또_결과에_해당하는_등수를_반환한다(boolean isBonusMatch, int matchCount, RankType expected) {
        RankType actual = RankType.calculateRankType(isBonusMatch, matchCount);

        assertThat(actual).isEqualTo(expected);
    }


}
