package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest(name = "당첨 번호 매치 개수와 보너스 번호 매치 여부로 Rank 반환(당첨인 경우)"
            + " - case : 당첨 번호 개수 {0}, 보너스 번호 포함 여부 {1}")
    @CsvSource(value = {"6,false,RANK_1", "5,true,RANK_2", "5,false,RANK_3", "4,true,RANK_4"})
    void getRank_If_Win(int winningNumbersMatchCount, boolean bonusNumberMatch, String rankName) {
        Assertions.assertThat(Rank.findRank(winningNumbersMatchCount, bonusNumberMatch))
                .isEqualTo(Rank.valueOf(rankName));
    }

    @ParameterizedTest(name = "당첨 번호 매치 개수와 보너스 번호 매치 여부로 Rank 반환(꽝인 경우)"
            + " - case : 당첨 번호 개수 {0}, 보너스 번호 포함 여부 {1}")
    @CsvSource(value = {"2,true", "1,false", "0,true"})
    void getRank_If_Lose(int winningNumbersMatchCount, boolean bonusNumberMatch) {
        Assertions.assertThat(Rank.findRank(winningNumbersMatchCount, bonusNumberMatch))
                .isEqualTo(Rank.RANK_OUT);
    }
}
