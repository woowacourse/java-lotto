package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoRankTest {
    @ParameterizedTest
    @CsvSource(value = {"true, true", "false, false"})
    @DisplayName("보너스 볼을 포함하면 2등이고 포함하지 않으면 3등이다.")
    void containsBonusBall(boolean contains, boolean expected) {
        assertThat(
                LottoRank.getRank(5, contains) == LottoRank.SECOND
        ).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 1, 0})
    @DisplayName("3개 미만의 정답을 맞힐 경우 FAILED를 반환한다")
    void correctUnder3Numbers(int winningNumberCount) {
        assertThat(
                LottoRank.getRank(winningNumberCount, false)
        ).isEqualTo(LottoRank.FAILED);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "6 - true - 2_000_000_000",
            "6 - false - 2_000_000_000",
            "5 - true - 30_000_000",
            "5 - false - 1_500_000",
            "4 - true - 50_000",
            "4 - false - 50_000",
            "3 - false - 5_000",
            "2 - false - 0"
    }, delimiterString = " - "
    )
    @DisplayName("등수에 맞는 상금을 반환한다")
    void returnPrize(int winningNumberCount, boolean containsBonusBall, int expected) {
        assertThat(LottoRank.getRank(winningNumberCount, containsBonusBall)
                .getPrizeMoney())
                .isEqualTo(expected);
    }
}
