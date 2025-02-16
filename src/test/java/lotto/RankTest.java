package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {
    @DisplayName("일치 개수와 보너스 번호 일치 여부에 따라서 Rank를 반환한다")
    @CsvSource(value = {"6:false:FIRST", "5:true:SECOND", "5:false:THIRD", "4:false:FOURTH", "3:false:FIFTH"},
            delimiterString = ":")
    @ParameterizedTest
    void 일치_개수와_보너스_번호_일치_여부에_따라서_Rank를_반환한다(int matchCount, boolean hasBonusNumber, Rank expected) {
        Rank result = Rank.checkRank(matchCount, hasBonusNumber);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("당첨 개수에 따른 해당 등수의 총 당첨금을 반환한다")
    @CsvSource(value = {"FIRST:0:0", "SECOND:2:60000000", "THIRD:3:4500000", "FOURTH:10:500000", "FIFTH:5:25000"},
            delimiterString = ":")
    @ParameterizedTest
    void 당첨_개수에_따른_해당_등수의_총_당첨금을_반환한다(Rank rank, int winningCount, int expected) {
        int result = rank.calculateAllWinningAmount(winningCount);
        assertThat(result).isEqualTo(expected);
    }
}
