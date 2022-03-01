package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @Test
    @DisplayName("당첨 번호 매치 개수와 보너스 번호 매치 여부로 Rank 반환(당첨인 경우)")
    void getRank() {
        Assertions.assertThat(Rank.getRank(5, true))
                .isEqualTo(Rank.RANK_2);
    }

    @ParameterizedTest(name = "당첨 번호 매치 개수와 보너스 번호 매치 여부로 Rank 반환(꽝인 경우)"
            + " - case : 당첨 번호 개수 {0}, 보너스 번호 포함 여부 {1}")
    @CsvSource(value = {"2,true", "0,false"})
    void getRank(int winningNumbersMatchCount, boolean bonusNumberMatch) {
        Assertions.assertThat(Rank.getRank(winningNumbersMatchCount, bonusNumberMatch))
                .isEqualTo(Rank.RANK_OUT);
    }
}
