package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @DisplayName("매치 카운트랑 보너스 카운트에 맞는 합당한 결과를 알려 주는지")
    @ParameterizedTest
    @CsvSource({"0,true,NONE", "6,false,FIRST", "5,true,SECOND", "5,false,THIRD"})
    void getCorrespondingRank(int matchCount, boolean bonusMatch, Rank rank) {
        assertThat(Rank.getCorrespondingRank(matchCount, bonusMatch)).isEqualTo(rank);
    }

    @DisplayName("당첨 통계를 위한 랭크만을 순서에 맞게 추출해 주는지")
    @Test
    void getRanksForStatistics() {
        assertThat(Rank.getRanksForStatistics()).isEqualTo(Arrays.asList(
                Rank.FIFTH,
                Rank.FOURTH,
                Rank.THIRD,
                Rank.SECOND,
                Rank.FIRST
        ));
    }
}
