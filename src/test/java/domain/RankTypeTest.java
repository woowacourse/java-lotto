package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTypeTest {

    @ParameterizedTest(name = "일치 개수: {0}, 보너스 일치 여부: {1}, 순위: {2}")
    @CsvSource({
            "3, false, 5",
            "4, false, 4",
            "5, false, 3",
            "5, true, 2",
            "6, false, 1"
    })
    void 일치_개수와_보너스_여부에_따라_등수를_판별한다(int matchNumber, boolean isBonusNumber, int expected) {
        assertThat(RankType.evaluateRank(matchNumber, isBonusNumber).getRank())
                .isEqualTo(expected);
    }
}
